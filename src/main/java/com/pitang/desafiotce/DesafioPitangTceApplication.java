package com.pitang.desafiotce;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pitang.desafiotce.domain.Car;
import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.repositories.CarRepository;
import com.pitang.desafiotce.repositories.UserRepository;

@SpringBootApplication
public class DesafioPitangTceApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private CarRepository carRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioPitangTceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		User user = new User(null, "Davi", "Pereira", "pereiradavi@gmail.com", 
				sdf.parse("07/09/1988 10:32"), "pereiradavi", pe.encode("123456"), "81999603923");
		User user2 = new User(null, "Novo", "User", "novouser@gmail.com", 
				sdf.parse("07/09/1988 10:32"), "novouser", pe.encode("123456"), "81999603923");
		
		
		Car car1 = new Car(null, 2020, "OYU8896", "VW UP", "Azul", user);
		Car car2 = new Car(null, 2020, "OYU8896", "VW UP", "Preto", user2);
		user.getCars().add(car1);
		user2.getCars().add(car2);
		
		this.userRepository.saveAll(Arrays.asList(user, user2));
		this.carRepository.saveAll(Arrays.asList(car1, car2));
	}  

}
