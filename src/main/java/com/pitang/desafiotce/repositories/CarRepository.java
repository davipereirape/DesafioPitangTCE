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

	/**
	 * finds and returns a car of user
	 * @param user
	 * @return car
	 */
	@Transactional(readOnly = true)
	List<Car> findByUser(User user);
	
	/**
	 * Finds and returns a car by id and user
	 * @param id
	 * @param user
	 * @return car
	 */
	@Transactional(readOnly = true)
	Optional<Car> findDistinctByIdAndUser(Integer id, User user);
	
	/**
	 * Finds and returns a car by license
	 * @param license
	 * @param user
	 * @return car
	 */
	@Transactional(readOnly = true)
	Optional<Car> findDistinctByLicensePlate(String license);
	
	/**
	 * Finds and returns a car by license and id
	 * @param license
	 * @param user
	 * @return car
	 */
	@Transactional(readOnly = true)
	Optional<Car> findDistinctByLicensePlateAndId(String license, Integer id);
	
}
