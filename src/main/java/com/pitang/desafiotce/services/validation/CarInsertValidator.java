package com.pitang.desafiotce.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.pitang.desafiotce.domain.Car;
import com.pitang.desafiotce.dto.CarDTO;
import com.pitang.desafiotce.resources.exception.FieldMessage;
import com.pitang.desafiotce.services.CarService;

public class CarInsertValidator implements ConstraintValidator<CarInsert, CarDTO> {
	
	@Autowired
	private CarService carService;
	 
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(CarInsert ann) {
	}

	@Override
	public boolean isValid(CarDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String idRequest = map.get("id");
		if (idRequest == null) {
			Car aux = carService.findByLicense(objDto.getLicensePlate());
			if (aux != null) 
				list.add(new FieldMessage("licensePlate", "License already exists"));
			
			for (FieldMessage e : list) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName())
					.addConstraintViolation(); 
			}
		}
		return list.isEmpty();
	}
}
