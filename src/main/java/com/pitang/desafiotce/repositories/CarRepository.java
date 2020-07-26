package com.pitang.desafiotce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.desafiotce.domain.Car;
import com.pitang.desafiotce.domain.User;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	@Transactional(readOnly = true)
	List<Car> findByUser(User user);
	
	@Transactional(readOnly = true)
	Optional<Car> findDistinctByIdAndUser(Integer id, User user);
	
}
