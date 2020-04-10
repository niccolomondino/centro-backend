package com.tecnositaf.centrobackend.service;

import java.time.LocalDate;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.repository.UserRepository;
import com.tecnositaf.centrobackend.utilities.CommonsUtility;

@Service
public class UserServiceImpl implements UserService {
	
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User getUser(User user) {
		return userRepository.getUser(user);
	}
	
	@Override
	public User getUserById(Integer idUser) {
		return userRepository.getUserById(idUser);
	}
	
	/*
	 *  Supportata da myBatis la Optional? No, non nella versione che < 3.5.0 ...
	@Override
	public User getUserByIdNoOptional(Integer idUser) {
		return (userRepository.getUserById(idUser).isPresent()) ? userRepository.getUserById(idUser).get() : null;

	}
	*/
	
	@Override
	public User createUser(User user) {
		userRepository.insert(user);
		return user;
	}


	@Override
	public List<User> getAllUsers() {
		return  userRepository.findAll();
		
	}

	@Override
	public int deleteAllUsers() {
		return userRepository.deleteAll();
	}
	
	
	@Override
	public List<User> filterUsersByBirthDate(LocalDate birthDate) {
		return userRepository.findUsersByBirthDate(birthDate);
	}

	@Override
	public int deleteUserById(Integer idUser) {
		if(CommonsUtility.isNull(getUserById(idUser))) 
			throw new ResourceNotFoundException(2,"ResourceNotFound");
		return userRepository.deleteById(idUser);
	}
	
	@Override
	public User updateUser(User user) {
		if(CommonsUtility.isNull(getUserById(user.getIdUser()))) 
			throw new ResourceNotFoundException(2,"ResourceNotFound");
		
		return createUser(user);
	}

	@Override
	public User updateUserWithId(User user, Integer idUser) {
		if(CommonsUtility.isNull(getUserById(idUser))) 
			throw new ResourceNotFoundException(2,"ResourceNotFound");
		
		return createUser(user);
	}



	
	

	

	
	

}
