package com.tecnositaf.centrobackend.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;


public class GetUsersResponse extends Response{


	private ArrayList<DTOUser> users;	//from ArrayList<User> to ArrayList<DTOUser>
	private int size;


	public GetUsersResponse(int code, String message) {
		super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.users = null;
		this.size = 0;
	}
	
	public GetUsersResponse(int code, String message, String path) {
		super(code, message, path);
		this.users = null;
		this.size = 0;
	}
	
	public GetUsersResponse(int code, String message, List<User> users, int size) {
		super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.users = new ArrayList<DTOUser>();	//il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);	
		this.size = size;
	}
	public GetUsersResponse(int code, String message, List<User> users) {
		super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.users = new ArrayList<DTOUser>();	//il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = users.size();
	}
	
	public List<DTOUser> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<DTOUser> users) {
		this.users = users;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "GetUsersResponse {users=" + users + ", size=" + size + "}";
	}
	
}
