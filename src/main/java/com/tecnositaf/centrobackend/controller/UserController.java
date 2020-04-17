package com.tecnositaf.centrobackend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.GetUserByIdResponse;
import com.tecnositaf.centrobackend.response.GetUsersResponse;
import com.tecnositaf.centrobackend.response.InsertNewUserResponse;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.service.UserService;
import com.tecnositaf.centrobackend.utilities.CommonsUtility;
import com.tecnositaf.centrobackend.utilities.UserUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DeviceController is a REST Controller that implements CRUD functionalities
 * for the domain User
 * 
 * @author niccolo mondino
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/*
	 * ************************************************************************** *
	 * ****************************** GET REQUESTS ****************************** *
	 * **************************************************************************
	 */

	@GetMapping
	public ResponseEntity<Response> getAllUsers() {
		logger.info("Get all Users...");
		List<User> users = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).headers(new HttpHeaders())
				.body(new GetUsersResponse(0, "SUCCESS", users));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getUserById(@PathVariable("id") Integer idUser) {

		if (CommonsUtility.isNull(idUser)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(-1, "Id User cannot be null.",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		User user = userService.getUserById(idUser);

		if (CommonsUtility.isNull(user))
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(-1, "User not found.",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		return ResponseEntity.status(HttpStatus.OK).body(new GetUserByIdResponse(0, "SUCCESS",
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
				user));
	}

	@GetMapping(value = "/filter/{birthDate}")
	public ResponseEntity<Response> filterUsersByBirtDate(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate) {

		if (CommonsUtility.isNull(birthDate)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(-1, "Birth date cannot be null.",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		List<User> filteredUsersByBirthDate = userService.filterUsersByBirthDate(birthDate);
		
		// non controllo niente perchè la lista puó essere vuota o null
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, "SUCCESS", filteredUsersByBirthDate));
	}

	/*
	 * ************************************************************************** *
	 * ***************************** POST REQUESTS ****************************** *
	 * **************************************************************************
	 */

	@PostMapping
	public ResponseEntity<InsertNewUserResponse> insertNewUser(@RequestBody DTOUser dtoUser) { 
																								
		logger.info("---------- POST /users ----------");

		if (!UserUtility.isValidForInsert(dtoUser)) { // validazione dell'input
			logger.info("INPUT VALIDATION ERROR - dtoUser => " + dtoUser.toString());
			throw new FailureException(Errors.INVALID_FIELD, HttpStatus.BAD_REQUEST);
		}

		User user = dtoUser.toUser();

		int numRowsInserted = userService.insertNewUserWithRowsInsertedCheck(user);
		logger.info("numRowsInserted => " + numRowsInserted);

		ArrayList<User> users = (ArrayList<User>) userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(new InsertNewUserResponse(0, "SUCCESS", user, users));
	}


	/*
	 * ************************************************************************** *
	 * **************************** DELETE REQUESTS ***************************** *
	 * **************************************************************************
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable("id") Integer idUser) {

		logger.info("Delete User with ID = " + idUser + "...");

		if (CommonsUtility.isNull(idUser)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(-1, "Id cannot be null",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		userService.deleteUserById(idUser);

		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, "SUCCESS", userService.getAllUsers()));
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<Response> deleteAllUsers() {
		logger.info("Delete All Users...");

		userService.deleteAllUsers();

		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetUsersResponse(0, "SUCCESS", userService.getAllUsers()));

	}

	/*
	 * ************************************************************************** *
	 * ***************************** PUT REQUESTS ******************************* *
	 * **************************************************************************
	 */

	@PutMapping
	public ResponseEntity<Response> updateUser(@RequestBody User user) {

		if (CommonsUtility.isNull(user) || CommonsUtility.isNull(user.getIdUser())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response(-1, "User or idUser cannot be null or empty",
							ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		userService.updateUser(user);

		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, "SUCCESS", userService.getAllUsers()));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Response> updateUser(@PathVariable("id") Integer idUser, @RequestBody User user) {

		if (CommonsUtility.isNull(user) || !CommonsUtility.isNull(user.getIdUser()) || CommonsUtility.isNull(idUser)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response(-1, "User or idUser cannot be null or empty",
							ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		userService.updateUserWithId(user, idUser);

		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, "SUCCESS", userService.getAllUsers()));

	}
}
