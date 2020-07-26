package com.pitang.desafiotce.services.exceptions;

public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String args) {
		super(args);
	}

	public AuthorizationException(String args, Throwable cause) {
		super (args, cause);
	}
}
