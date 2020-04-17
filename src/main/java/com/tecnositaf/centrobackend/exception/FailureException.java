package com.tecnositaf.centrobackend.exception;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.Errors;

public class FailureException extends RuntimeException{

		private static final long serialVersionUID = 1L;
		
	    private Errors error;
		private HttpStatus httpStatus;
	 
		public FailureException() {
			super();
		}
		public FailureException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
			super(arg0, arg1, arg2, arg3);
		}
		public FailureException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}
		public FailureException(String arg0) {
			super(arg0);
		}
		public FailureException(Throwable arg0) {
			super(arg0);
		}
	    
	 
	    public FailureException(Errors error, HttpStatus httpStatus) {
	    	super();
	        this.error = error;
	        this.httpStatus = httpStatus;
	    }
	    
	    public FailureException(String message, Errors error, HttpStatus httpStatus) {
	    	super(message);
	        this.error = error;
	        this.httpStatus = httpStatus;
	    }
	 
	    
	    
	    public HttpStatus getHttpStatus() {
			return httpStatus;
		}

		public void setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
		}
		
		public Errors getResponseErrorEnum() {
			return error;
		}
		public void setResponseErrorEnum(Errors error) {
			this.error = error;
		}

		
		@Override
		public String toString() {
			return "FailureException {httpStatus=" + httpStatus + ", responseErrorEnum=" + error + "}";
		}

}
