package com.pitang.desafiotce.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Entity of Car
 */
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private Integer year;
	
	@Getter
	@Setter
	@Column(unique = true)
	private String licensePlate;
	
	@Getter
	@Setter
	private String model;
	
	@Getter
	@Setter
	private String color;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	@Getter
	@Setter
	@JsonIgnore
	private User user;

}
