package com.pitang.desafiotce.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Entity of User
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
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
	@Column(unique = true)
	private String email;
	
	@Getter
	@Setter
	private Date birthDay;
	
	@Getter
	@Setter
	@Column(unique = true)
	private String login;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String phone;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "user")
	private List<Car> cars = new ArrayList<Car>();
	
	/**
	 * Construct of User without cars
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param birthDay
	 * @param login
	 * @param password
	 * @param phone
	 */
	public User(Integer id, String firstName, String lastName, String email, 
			Date birthDay, String login, String password, String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDay = birthDay;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}
	
}
