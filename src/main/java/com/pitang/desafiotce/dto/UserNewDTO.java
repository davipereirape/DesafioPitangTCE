package com.pitang.desafiotce.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.services.validation.UserInsert;
import com.pitang.desafiotce.services.validation.UserUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Data Transfer Object for a new user 
 */
@NoArgsConstructor
@UserInsert
@UserUpdate
public class UserNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
 
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	private String firstName;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	private String lastName;
	
	@Getter
	@Setter
	@Email (message = "Invalid field")
	@NotEmpty(message = "Missing field")
	private String email;
	
	@Getter
	@Setter
	@NotNull(message = "Missing field")
	private Date birthDay;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	@Length(min = 9, max = 11, message = "Please enter a minimum of 9 or a maximum of 11 characters")
	private String phone;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	private String login;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	private String password;
	
	/**
	 * Constructor of UserNewDto. 
	 * Sets the fields enable to see in searchies of users
	 * 
	 * @param User
	 */
	public UserNewDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthDay = user.getBirthDay();
		this.phone = user.getPhone();
		this.login = user.getLogin();
		this.password = user.getPassword();
	}
}
