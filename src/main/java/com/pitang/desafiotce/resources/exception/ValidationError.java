package com.pitang.desafiotce.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessageErrorCode> errors = new ArrayList<FieldMessageErrorCode>();
	
	public ValidationError(Integer status, String msg) {
		super(status, msg);
	}
	
	public List<FieldMessageErrorCode> getErros() {
		return this.errors;
	}
	 
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessageErrorCode(fieldName, message));
	}

}
