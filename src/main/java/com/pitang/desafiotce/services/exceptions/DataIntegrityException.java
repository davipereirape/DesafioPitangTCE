package com.pitang.desafiotce.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String args) {
		super(args);
	}

	public DataIntegrityException(String args, Throwable cause) {
		super (args, cause);
	}
}
