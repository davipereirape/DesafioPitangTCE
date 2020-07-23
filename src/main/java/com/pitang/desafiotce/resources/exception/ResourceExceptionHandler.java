package com.pitang.desafiotce.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pitang.desafiotce.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
/**
 * @author Davi Pereira <pereiradavi@gmail.com>
 * @since July of 2020
 * 
 * Catch and handle exceptions of system / httpRequest
 */
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
//	@ExceptionHandler(DataIntegrityException.class)
//	public ResponseEntity<StandardError> dataIntegrity (DataIntegrityException e, HttpServletRequest request) {
//		
//		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
//	}
//	

	
}
