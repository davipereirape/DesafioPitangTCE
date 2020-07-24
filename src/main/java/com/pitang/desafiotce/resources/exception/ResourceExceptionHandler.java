package com.pitang.desafiotce.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pitang.desafiotce.services.exceptions.DataIntegrityException;
import com.pitang.desafiotce.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
/**
 * @author Davi Pereira <pereiradavi@gmail.com>
 * @since July of 2020
 * 
 * Catch and handle exceptions of system / httpRequest
 */
public class ResourceExceptionHandler {

	/**
	 * Process a ObjectNotFoundException and create the message matching
	 * @param ObjectNotFoundException
	 * @param HttpServletRequest
	 * 
	 * @return ResponseEntity<StandardError>
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	/**
	 * Process a ObjectNotFoundException and create the message matching
	 * @param ObjectNotFoundException
	 * @param HttpServletRequest
	 * 
	 * @return ResponseEntity<StandardError>
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound (UsernameNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	/**
	 * Process a DataIntegrityException and create the message matching
	 * @param DataIntegrityException
	 * @param HttpServletRequest
	 * 
	 * @return ResponseEntity<StandardError>
	 */
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity (DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	/**
	 * Process a MethodArgumentNotValidException and create the messages matching
	 * @param MethodArgumentNotValidException
	 * @param HttpServletRequest
	 * 
	 * @return ResponseEntity<StandardError>
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation (MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError validationError = new ValidationError(
				HttpStatus.NOT_FOUND.value(), "Validation error");
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			validationError.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
	
}
