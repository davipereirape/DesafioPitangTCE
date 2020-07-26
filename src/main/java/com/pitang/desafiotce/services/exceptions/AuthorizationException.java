package com.pitang.desafiotce.services.exceptions;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Exception for Authorization fail.
 */
public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String args) {
		super(args);
	}

	public AuthorizationException(String args, Throwable cause) {
		super (args, cause);
	}
}
