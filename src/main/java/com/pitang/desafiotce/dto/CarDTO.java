package com.pitang.desafiotce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pitang.desafiotce.domain.Car;
import com.pitang.desafiotce.services.validation.CarInsert;
import com.pitang.desafiotce.services.validation.CarUpdate;

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
@CarInsert
@CarUpdate
public class CarDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	@NotNull(message = "Missing field")
	private Integer year;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	@Length(min = 8, max = 8, message = "Invalid field")
	private String licensePlate;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	private String model;
	
	@Getter
	@Setter
	@NotEmpty(message = "Missing field")
	private String color;
	
	@Getter
	@Setter
	private Integer userId;
	
	public CarDTO(Car car) {
		this.id = car.getId();
		this.color = car.getColor();
		this.licensePlate = car.getLicensePlate();
		this.model = car.getModel();
		this.userId = car.getUser().getId();
		this.year = car.getYear();
	}

}
