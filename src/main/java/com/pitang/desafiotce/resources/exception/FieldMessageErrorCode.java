package com.pitang.desafiotce.resources.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * Encapsulates the fields, error messages and errorCode corresponding to them.
 */
@NoArgsConstructor
public class FieldMessageErrorCode extends FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String errorCode;
	
	public FieldMessageErrorCode(String fieldName, String message) {
		super(fieldName, message);
		this.errorCode = this.findErrorCode(message);
	}

	private String findErrorCode(String message) {
		if (message != null) {
			if (message.equals(EMAIL_ALREADY_EXISTS)) 
				return "2";
			if (message.equals(LOGIN_ALREADY_EXISTS)) 
				return "3";
			if (message.equals(INVALID_FIELDS)) 
				return "4";
			if (message.equals(MISSING_FIELDS)) 
				return "5";
			if (message.contains(MINIMUN_MAXIMUM))
				return "10";
		}
		return "";
	}	
	
	// TODO criar ENUM
	private static String EMAIL_ALREADY_EXISTS = "Email already exists";
	private static String LOGIN_ALREADY_EXISTS = "Login already exists";
	private static String INVALID_FIELDS = "Invalid field";
	private static String MISSING_FIELDS =  "Missing field";
	private static String MINIMUN_MAXIMUM    =  "minimum";
}
