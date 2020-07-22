package com.pitang.desafiotce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.desafiotce.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
