package com.tecnositaf.centrobackend.response;

import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.model.User;

public class LoginResponse extends Response{

	private User userLogged;
	private List<User> users;
	private int numberOfUsers;
	
	
	public LoginResponse(int code, String message, String path, User userLogged, List<User> users) {
		super(code, message, path);
		this.userLogged = userLogged;
		this.users = users;
		this.numberOfUsers = users.size();
	}
	
	public LoginResponse(int code, String message, String path) {
		super(code, message, path);
	}
	
	
	public LoginResponse(User userLogged, List<User> users) {
		super(0, "GetLoginResponse successful", ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.userLogged = userLogged;
		this.users = users;
		this.numberOfUsers = users.size();
	}
	
	public User getUserLogged() {
		return userLogged;
	}
	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
	
}
