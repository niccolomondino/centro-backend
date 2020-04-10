package com.tecnositaf.centrobackend.response;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.model.User;

public class GetUserByIdResponse extends Response{

private User user;
	
	public GetUserByIdResponse(int code, String message, String path) {
		super(code, message, path);
	}

	public GetUserByIdResponse(User user) {
		super(2,"Get User by ID Response Successful", ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
