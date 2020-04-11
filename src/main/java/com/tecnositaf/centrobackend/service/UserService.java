package com.tecnositaf.centrobackend.service;

import java.time.LocalDate;
import java.util.List;

import com.tecnositaf.centrobackend.model.User;

public interface UserService {
	
	User getUser(User user);
	
	User getUserById(Integer idUser);
		
	List<User> getAllUsers();
	
	int insertNewUser(User user);
	
	int insertNewUserWithRowsInsertedCheck(User user);
	
	int deleteAllUsers();
	
	int deleteUserById(Integer idUser);

	List<User> filterUsersByBirthDate(LocalDate birthDate);
	
	User updateUser(User user);

	User updateUserWithId(User user, Integer idUser);

	

	

	
}
