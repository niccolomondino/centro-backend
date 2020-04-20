package com.tecnositaf.centrobackend.repository;


import java.time.LocalDate;
import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.mapper.UserMapper;
import com.tecnositaf.centrobackend.model.User;


@Repository
public class UserRepository {
	
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserMapper userMapper;
	

	public User getUser(User user) {
		return userMapper.getUser(user);
	}
	
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}
	
	public List<User> findAll() {
		return userMapper.findAll(); 
	}
	
	public List<User> findUsersByBirthDate(LocalDate birthDate){
		return userMapper.findByBirthDate(birthDate);
	}

    public int insert(User user) {
    	return userMapper.insert(user);
    }

	public int deleteAll() {
		return userMapper.deleteAll();
	}

	public int deleteById(Integer idUser) {
		return userMapper.deleteById(idUser);
	}
	
	public User update(User user) {
    	return userMapper.update(user);
    }
	
	public User updateWithId(User user) {
    	return userMapper.update(user);
    }


	
}
