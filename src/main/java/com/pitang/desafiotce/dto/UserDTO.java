package com.pitang.desafiotce.dto;

import java.io.Serializable;
import java.util.Date;

import com.pitang.desafiotce.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Data Transfer Object of User *
 */
@NoArgsConstructor
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private String email;
	
	@Getter
	@Setter
	private Date birthDay;
	
	@Getter
	@Setter
	private String login;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String phone;
	
	public UserDTO(User user) {
		// TODO Put the necessary fields.
	}
}
