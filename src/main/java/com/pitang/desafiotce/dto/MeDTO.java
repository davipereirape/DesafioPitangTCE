package com.pitang.desafiotce.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pitang.desafiotce.domain.Car;
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
 * Data Transfer Object of User/Me *
 */
@NoArgsConstructor
@UserInsert
@UserUpdate
public class MeDTO implements Serializable{
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
	
	@Getter
	@Setter
	private String login;
	
	@Getter
	@Setter
	private List<Car> cars = new ArrayList<Car>();
	
	@Setter
	@Getter
	private String createdAt;

	@Setter
	@Getter
	private String lastLogin;
	
	/**
	 * Constructor of MeDto. 
	 * Sets the fields enable to see in searches of users
	 * 
	 * @param User
	 */
	public MeDTO(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthDay = user.getBirthDay();
		this.phone = user.getPhone();
		this.login = user.getLogin();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = simpleDateFormat.format(user.getCreatedAt());
		this.createdAt = date;
		
		if (user.getLastLogin() != null) {
			String dateLastLogin = simpleDateFormat.format(user.getLastLogin());
			this.lastLogin = dateLastLogin;
		} else {
			this.lastLogin = "";
		}
	}
}
