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

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String idRequest = map.get("id");
		if (idRequest == null) {
			User aux = userRepository.findByEmail(objDto.getEmail());
			if (aux != null) 
				list.add(new FieldMessage("email", "Email already exists"));
			
			User auxLogin = userRepository.findByLogin(objDto.getLogin());
			if (auxLogin != null) 
				list.add(new FieldMessage("login", "Login already exists"));
			
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
