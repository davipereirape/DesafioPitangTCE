package com.pitang.desafiotce.services.exceptions;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Exception Object not found .
 */
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String args) {
		super(args);
	}

	public ObjectNotFoundException(String args, Throwable cause) {
		super (args, cause);
	}
}
