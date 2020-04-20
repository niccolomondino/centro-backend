package com.tecnositaf.centrobackend.response;



import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;

public class GetUserByIdResponse extends Response{

	private DTOUser user;
	
	public GetUserByIdResponse(int code, String message, String path) {
		super(code, message, path);
		this.user = null;
	}

	public GetUserByIdResponse(int code, String message, String path, User user) {
		super(code, message, path);
		this.user = user.toDTOUser();
	}

	public DTOUser getUser() {
		return user;
	}

	public void setUser(DTOUser user) {
		this.user = user;
	}
}
