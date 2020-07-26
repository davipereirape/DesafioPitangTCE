package com.pitang.desafiotce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.dto.UserNewDTO;
import com.pitang.desafiotce.repositories.UserRepository;
import com.pitang.desafiotce.security.UserSS;
import com.pitang.desafiotce.services.exceptions.AuthorizationException;
import com.pitang.desafiotce.services.exceptions.DataIntegrityException;
import com.pitang.desafiotce.services.exceptions.ObjectNotFoundException;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 *        Service of users.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	/**
	 * Returns the user authenticated with token informed
	 * @return UserSS
	 */
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	public User searchUserAuthenticated(UserSS userSS)
	{
		if (userSS == null || userSS.getId() == null) {
			throw new AuthorizationException("Unauthorized");
		} 
		
		Optional<User> optionUser = userRepository.findById(userSS.getId());
		return optionUser.orElseThrow(() -> new AuthorizationException("Unauthorized "));
	}
	
	/**
	 * Finds and returns all user register of the system.
	 * 
	 * @return List of Users
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Finds and returns a specific user.
	 * 
	 * @param id
	 * @return User
	 * @throws ObjectNotFoundException
	 */
	public User find(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("User not found! Id: " + id));
	}

	/**
	 * Saves a User and returns the id generated
	 * 
	 * @param user
	 * @return User
	 */
	public User insert(User user) {
		user.setId(null);
		return userRepository.save(user);
	}

	/**
	 * Constructs a User object.
	 * 
	 * @param UserDTO
	 * @return User
	 */
	public User fromDTO(UserNewDTO objDTO) {
		User user = new User(objDTO.getId(), objDTO.getFirstName(), objDTO.getLastName(), objDTO.getEmail(),
				objDTO.getBirthDay(), objDTO.getLogin(), pe.encode(objDTO.getPassword()), objDTO.getPhone());

		return user;
	}
	
	/**
	 * Updates a User and return him
	 * 
	 * @param user
	 * @return User
	 */
	public User update(User user) {
		User newObj = this.find(user.getId());
		this.updateDate(newObj, user);
		return this.userRepository.save(newObj);
	}

	/**
	 * Delete a specific User through his Id
	 * @param id
	 */
	public void delete(Integer id) {
		this.find(id);

		try {
			this.userRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is not possible to delete a user who owns car");
		}
	}

	/**
	 *  Updates the searched object with information about the object that needs to be updated.
	 * 
	 * @param newObj
	 * @param obj
	 */
	private void updateDate(User newObj, User obj) {
		newObj.setBirthDay(obj.getBirthDay());
		newObj.setEmail(obj.getEmail());
		newObj.setFirstName(obj.getFirstName());
		newObj.setLastName(obj.getLastName());
		newObj.setLogin(obj.getLogin());
		newObj.setPassword(obj.getPassword());
		newObj.setPhone(obj.getPhone());
	}
}
