package com.tecnositaf.centrobackend.response;

import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.model.User;

public class GetUsersResponse extends Response{

	private int numberOfUsers;
	private List<User> users;

	
	
	public GetUsersResponse(int code, String message, String path) {
		super(code, message, path);
	}

	public GetUsersResponse(List<User> users) {
		super(2,"Get Users Response Successful", ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.users = users;
		this.numberOfUsers = users.size();
	}
	
	public GetUsersResponse(List<User> users, String message) {
		super(2,message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.users = users;
		this.numberOfUsers = users.size();
	}
	
	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
}
