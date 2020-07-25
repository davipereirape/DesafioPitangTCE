package com.pitang.desafiotce.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pitang.desafiotce.domain.Car;
import com.pitang.desafiotce.dto.CarDTO;
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
	
	/**
	 * Finds and returns all cars of user.
	 * 
	 * @return List Cars of User
	 */
	@RequestMapping (method = RequestMethod.GET)
	public ResponseEntity<List<CarDTO>> findAll(){
		List<Car> listCar = service.findAll();
		List<CarDTO> listDto 
			= listCar.stream().map(obj -> new CarDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
	
	/**
	 * Finds and returns a specific car of user. 
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CarDTO> find(@PathVariable Integer id) {
		
		Car car = service.find(id);
		CarDTO objDto = new CarDTO(car);
		return ResponseEntity.ok().body(objDto);
	}
	
	/**
	 * Insert a new Car of user
	 * 
	 * @param CarDTO
	 * @return ResponseEntity
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CarDTO objDTO) {
		objDTO = new CarDTO(service.insert(service.fromDTO(objDTO)));
		
		URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objDTO.getId()).toUri();
		
		return ResponseEntity.created(url).build();
	}
	
	/**
	 * Update a specific car of user
	 * 
	 * @param objDTO
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CarDTO objDTO, @PathVariable Integer id) {
		objDTO.setId(id);
		objDTO = new CarDTO(service.update(service.fromDTO(objDTO)));
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Delete a specific car of user through car Id
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
