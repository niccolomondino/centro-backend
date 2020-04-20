package com.tecnositaf.centrobackend.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.response.Response;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@ExceptionHandler(value = { ResourceNotFoundException.class})
	protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, 
															HttpServletRequest request) {
		
		logger.error("Resource Not Found Exception");
		
		Response bodyOfResponse = new Response(ex.getMessage(),ex.getErrCode(), 
								               ex.getErrDescription(), request.getRequestURI());
		
		return ResponseEntity.status(ex.getHttpStatus()).headers( new HttpHeaders() )
														  .body(bodyOfResponse);
	}
	
	/** Global exception handler. Provides handling for 
	 *  all exceptions throughout this application. */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllException(RuntimeException ex, 
														HttpServletRequest request) {
		
		logger.error("UNEXPECTED ERROR", ex);
		
		Response bodyOfResponse = new Response("This a general exception.", -1, 
										       "EXCEPTION", request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							 .headers( new HttpHeaders() ).body(bodyOfResponse);
	}

	
	
	@ExceptionHandler(value = { FailureException.class })
	protected ResponseEntity<Object> handleFailureException(FailureException ex, HttpServletRequest request) {
		
		logger.error("FAILURE EXCEPTION", ex);
		
		Response bodyOfResponse = new Response(ex.getMessage(),ex.getResponseErrorEnum().code, 
											   ex.getResponseErrorEnum().description, request.getRequestURI());
		
		return ResponseEntity.status(ex.getHttpStatus()).headers( new HttpHeaders() ).body(bodyOfResponse);
	}
	
}