package com.pitang.desafiotce.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * Object of exhibition of status and error message in the client.
 */
@AllArgsConstructor
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private Integer status;
	
	@Getter
	@Setter
	private String msg;
}
