package com.pitang.desafiotce.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Data Transfer Object of Credentils *
 */
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String login;
	
	@Getter
	@Setter
	private String password;
	
}
