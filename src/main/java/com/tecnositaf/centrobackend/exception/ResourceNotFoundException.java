package com.tecnositaf.centrobackend.exception;

import com.tecnositaf.centrobackend.enumeration.Errors;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
    private  Errors errCode;
    private  String errMsg;
 
    public ResourceNotFoundException() { }
 
    public ResourceNotFoundException(Errors errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
 
    public Errors getErrCode() {
        return errCode;
    }
 
    public void setErrCode(Errors errCode) {
        this.errCode = errCode;
    }
 
    public String getErrMsg() {
        return errMsg;
    }
 
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }   
}
