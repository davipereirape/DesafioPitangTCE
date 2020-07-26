package com.pitang.desafiotce.services.exceptions;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Exception for fail of Data of Integrity .
 */
public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String args) {
		super(args);
	}

	public DataIntegrityException(String args, Throwable cause) {
		super (args, cause);
	}
}
