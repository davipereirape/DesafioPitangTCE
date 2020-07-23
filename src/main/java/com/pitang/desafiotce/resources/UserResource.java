package com.pitang.desafiotce.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.dto.UserDTO;
import com.pitang.desafiotce.services.UserService;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * API of users
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	/**
	 * Finds and returns all user register of the system.
	 * 
	 * @return List of Users
	 */
	@RequestMapping (method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> listUser = service.findAll();
		List<UserDTO> listDto 
			= listUser.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
	
	/**
	 * Finds and returns a specific user. 
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		User user = service.find(id);
		UserDTO objDto = new UserDTO(user);
		return ResponseEntity.ok().body(objDto);
	}
	
	/**
	 * Insert a new User
	 * 
	 * @param UserDTO
	 * @return ResponseEntity
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		objDTO = new UserDTO(service.insert(service.fromDTO(objDTO)));
		
		URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objDTO.getId()).toUri();
		
		return ResponseEntity.created(url).build();
	}
	
	/**
	 * Update a specific User
	 * 
	 * @param objDTO
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable Integer id) {
		objDTO.setId(id);
		objDTO = new UserDTO(service.update(service.fromDTO(objDTO)));
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Delete a specific User through his Id
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
