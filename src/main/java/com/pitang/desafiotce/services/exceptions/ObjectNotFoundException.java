package com.pitang.desafiotce.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String args) {
		super(args);
	}

	public ObjectNotFoundException(String args, Throwable cause) {
		super (args, cause);
	}
}
