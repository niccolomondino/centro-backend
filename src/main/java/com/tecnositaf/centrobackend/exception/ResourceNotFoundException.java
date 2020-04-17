package com.tecnositaf.centrobackend.exception;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.Errors;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
    private  int errCode;
    private  String errDescription;
    private  HttpStatus httpStatus;
 
    public ResourceNotFoundException() { }
 
    public ResourceNotFoundException(Errors error) {
    	super();
        this.errCode = error.code;
        this.errDescription = error.description;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
    
    public ResourceNotFoundException(String message, Errors error, HttpStatus httpStatus) {
    	super(message);
        this.errCode = error.code;
        this.errDescription = error.description;
        this.httpStatus = httpStatus;
    }
 
    public int getErrCode() {
        return errCode;
    }
 
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
 
    public String getErrDescription() {
        return errDescription;
    }
 
    public void setErrDescription(String errMsg) {
        this.errDescription = errMsg;
    }   
    
    public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
