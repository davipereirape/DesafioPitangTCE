package com.pitang.desafiotce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.repositories.UserRepository;
import com.pitang.desafiotce.security.UserSS;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * UserDetailsService necessary in framework spring boot authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	/**
	 * Search the user informed to check the credentials
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = repo.findByLogin(login);
		
		if (user == null) {
			throw new UsernameNotFoundException(login);
		}
		
		return new UserSS(user.getId(), user.getLogin(), user.getPassword());
	}

}
