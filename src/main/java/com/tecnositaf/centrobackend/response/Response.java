package com.tecnositaf.centrobackend.response;

import java.sql.Timestamp;
import java.time.ZoneOffset;

import com.tecnositaf.centrobackend.utilities.DateUtility;

public class Response {
	
	private int code;
	private String message;
	private Timestamp timestamp;
	private String path;
	
	
	public Response(int code, String message, String path) {
		this.code = code;
		this.message = message;
		this.timestamp = new Timestamp(DateUtility.getCurrentEpochTime(ZoneOffset.UTC));
		this.path = path;
	}
	
	public Response() {
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


}
