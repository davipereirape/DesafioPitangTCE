package com.pitang.desafiotce.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.pitang.desafiotce.domain.User;
import com.pitang.desafiotce.dto.UserNewDTO;
import com.pitang.desafiotce.repositories.UserRepository;
import com.pitang.desafiotce.resources.exception.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdate, UserNewDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UserUpdate ann) {
	}

	@Override
	public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String idRequest = map.get("id");
		if (idRequest != null) {
			Integer uriId = Integer.parseInt(map.get("id"));
			
			User aux = userRepository.findByEmail(objDto.getEmail());
			if (aux != null && !aux.getId().equals(uriId)) 
				list.add(new FieldMessage("email", "Email already exists!"));
			
			User auxLogin = userRepository.findByLogin(objDto.getLogin());
			if (auxLogin != null && !auxLogin.getId().equals(uriId)) 
				list.add(new FieldMessage("login", "Login already exists"));
			
			for (FieldMessage e : list) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
						.addConstraintViolation();
			}
		}
		
		return list.isEmpty();
	}
}
