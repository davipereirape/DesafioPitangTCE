package com.pitang.desafiotce.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
	private String login;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String phone;
	
//	private List<Car>
	
}
