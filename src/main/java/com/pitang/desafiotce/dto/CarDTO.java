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
 * Data Transfer Object of Car
 */
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private Integer year;
	
	@Getter
	@Setter
	private String licensePlate;
	
	@Getter
	@Setter
	private String model;
	
	@Getter
	@Setter
	private String color;
	
	@Getter
	@Setter
	private UserDTO user;

}
