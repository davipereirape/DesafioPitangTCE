package com.pitang.desafiotce.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
}
