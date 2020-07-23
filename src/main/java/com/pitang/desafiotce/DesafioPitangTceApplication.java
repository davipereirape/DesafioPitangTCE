package com.pitang.desafiotce;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.repositories.UserRepository;

@SpringBootApplication
public class DesafioPitangTceApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioPitangTceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		User user = new User(null, "Davi", "Pereira", "pereiradavi@gmail.com", 
				sdf.parse("07/09/1988 10:32"), "pereiradavi", "123456", "+5581999603923");
		
		this.userRepository.save(user);
	}

}
