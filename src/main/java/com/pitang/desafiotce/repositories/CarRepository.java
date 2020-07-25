package com.pitang.desafiotce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.desafiotce.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

		
}
