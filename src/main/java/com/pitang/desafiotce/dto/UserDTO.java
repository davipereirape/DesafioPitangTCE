package com.pitang.desafiotce.dto;

import java.io.Serializable;
import java.util.Date;

import com.pitang.desafiotce.domain.User;

import antlr.StringUtils;
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
	private String phone;
	
	@Setter
	@Getter
	private String login;
	
	@Setter
	@Getter
	private String password;
	
	/**
	 * Constructor of UserDto. 
	 * Sets the fields enable to see in searchies of users
	 * 
	 * @param User
	 */
	public UserDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthDay = user.getBirthDay();
		this.phone = user.getPhone();
		this.login = "";
		this.password = "";
	}
}
