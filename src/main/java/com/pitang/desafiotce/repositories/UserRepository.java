package com.pitang.desafiotce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.desafiotce.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Transactional(readOnly = true)
	User findByEmail(String email);
	
	@Transactional(readOnly = true)
	User findByLogin(String login);
	
}
