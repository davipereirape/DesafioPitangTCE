package com.pitang.desafiotce.resources.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * Encapsulates the fields and error messages corresponding to them.
 */
@NoArgsConstructor
public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String fieldName;
	
	@Getter
	@Setter
	private String message;
	
	@Getter
	private String errorCode;
	
	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
}
