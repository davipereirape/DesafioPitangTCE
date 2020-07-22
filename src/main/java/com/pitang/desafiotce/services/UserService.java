package com.pitang.desafiotce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.repositories.UserRepository;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * Service of users.
 */
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Finds and returns all user register of the system.
	 * @return List of Users
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
}
