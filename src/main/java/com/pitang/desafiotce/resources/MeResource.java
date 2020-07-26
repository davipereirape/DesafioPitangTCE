package com.pitang.desafiotce.resources;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.dto.MeDTO;
import com.pitang.desafiotce.security.UserSS;
import com.pitang.desafiotce.services.UserService;

@RestController
@RequestMapping(value="/me")
public class MeResource {
	
	@Autowired
	private UserService service;
	
	/**
	 * Finds and returns informations about the user. 
	 * 
	 * @param id
	 * @return ResponseEntity
	 * @throws ParseException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MeDTO> findMe() {
		UserSS userSS = UserService.authenticated();
		User user = service.find(userSS.getId()); 
		MeDTO objDto = new MeDTO(user);
		
		objDto.getCars().addAll(user.getCars());
		return ResponseEntity.ok().body(objDto);
	}

}
