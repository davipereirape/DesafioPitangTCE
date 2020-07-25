package com.pitang.desafiotce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.desafiotce.domain.Car;
import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.dto.CarDTO;
import com.pitang.desafiotce.repositories.CarRepository;
import com.pitang.desafiotce.repositories.UserRepository;
import com.pitang.desafiotce.services.exceptions.ObjectNotFoundException;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 *
 * Service of car.
 */
@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	@Autowired UserRepository userRepository;
	
	/**
	 * Finds and returns all cars of user.
	 * 
	 * @return List of Cars
	 */
	public List<Car> findAll() {
		return carRepository.findAll();
	}
	
	/**
	 * Finds and returns a specific car of user.
	 * 
	 * @param id
	 * @return Car
	 * @throws ObjectNotFoundException
	 */
	public Car find(Integer id) {
		Optional<Car> obj = carRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Car not found! Id: " + id));
	}
	
	/**
	 * Saves a Car and returns the id generated
	 * 
	 * @param car
	 * @return Car
	 */
	public Car insert(Car car) {
		car.setId(null);
		return carRepository.save(car);
	}
	
	/**
	 * Updates a car and return him
	 * 
	 * @param car
	 * @return Car
	 */
	public Car update(Car car) {
		Car newObj = this.find(car.getId());
		this.updateDate(newObj, car);
		return this.carRepository.save(newObj);
	}
	
	/**
	 * Delete a specific car of user through car Id
	 * @param id
	 */
	public void delete(Integer id) {
		this.find(id);
		this.carRepository.deleteById(id);
	}
	
	/**
	 * Constructs a Car object.
	 * 
	 * @param CarDTO
	 * @return Car
	 */
	public Car fromDTO(CarDTO objDTO) {
		Optional<User> user = userRepository.findById(1); // TODO recuperar usuário logado e validar
		Car car = new Car(objDTO.getId(), objDTO.getYear(), objDTO.getLicensePlate(), objDTO.getModel(), objDTO.getColor(), user.orElse(null));

		return car;
	}
	
	/**
	 * Updates the searched object with information about the object that needs to be updated.
	 * 
	 * @param newObj - Database car
	 * @param obj - Car with new data
	 */
	private void updateDate(Car newCar, Car car) {
		newCar.setColor(car.getColor());
		newCar.setId(car.getId());
		newCar.setLicensePlate(car.getLicensePlate());
		newCar.setModel(car.getModel());
		newCar.setYear(car.getYear());
	}
	
}
