package com.tecnositaf.centrobackend.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;

public class InsertNewUserResponse extends Response{

	private DTOUser userInserted;
	private ArrayList<DTOUser> users;	
	private int size;
	
	public InsertNewUserResponse(int code, String message) {
		super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.userInserted = null;
		this.users = null;
		this.size = 0;
	}

	public InsertNewUserResponse(int code, String message, User userInserted, List<User> users, int size) {
		super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.userInserted = userInserted.toDTOUser();
		this.users = new ArrayList<>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = size;
	}
	public InsertNewUserResponse(int code, String message, User userInserted, List<User> users) {
		super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.userInserted = userInserted.toDTOUser();
		this.users = new ArrayList<>();
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = users.size();
	}

	
	
	public DTOUser getUserInserted() {
		return userInserted;
	}

	public void setUserInserted(DTOUser userInserted) {
		this.userInserted = userInserted;
	}

	public List<DTOUser> getUsers() {
		return users;
	}

	public void setUsers(List<DTOUser> users) {
		this.users = (ArrayList<DTOUser>) users;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	
	@Override
	public String toString() {
		return "InsertNewUserResponse {userInserted=" + userInserted + ", users=" + users + ", size=" + size + "}";
	}
}
