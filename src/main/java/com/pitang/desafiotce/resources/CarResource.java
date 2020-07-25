package com.pitang.desafiotce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.desafiotce.services.CarService;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * API of cars
 */
@RestController
@RequestMapping(value = "/cars")
public class CarResource {

	@Autowired
	private CarService service;
	
}
