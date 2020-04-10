package com.tecnositaf.centrobackend.exception;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.Errors;

public class FailureException extends RuntimeException{

		private static final long serialVersionUID = 1L;
		
	    private int errCode;
	    private String errMsg;
		private HttpStatus httpStatus;
	 
	    public FailureException() { }
	    
	    public FailureException(Errors error) {
	    	super(error.description);
	        this.errCode = error.code;
	        this.errMsg = error.description;
	        this.httpStatus = HttpStatus.CONFLICT;
	    }
	 
	    public FailureException(Errors error, HttpStatus httpStatus) {
	    	super(error.description);
	        this.errCode = error.code;
	        this.errMsg = error.description;
	        this.httpStatus = httpStatus;
	    }
	 
	    public int getErrCode() {
	        return errCode;
	    }
	 
	    public void setErrCode(int errCode) {
	        this.errCode = errCode;
	    }
	 
	    public String getErrMsg() {
	        return errMsg;
	    }
	 
	    public void setErrMsg(String errMsg) {
	        this.errMsg = errMsg;
	    }
	    
	    public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		public void setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
		}

}
