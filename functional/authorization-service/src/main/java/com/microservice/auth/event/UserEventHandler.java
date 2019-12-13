package com.microservice.auth.event;

import com.microservice.auth.repository.UserRepository;
import com.microservice.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import java.util.Set;

/**
 *
 */
@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class UserEventHandler {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@HandleBeforeCreate
	@HandleBeforeSave
	public void handleBeforeCreateOrSave(User user) {
		validate(user);
		if (!userRepository.findByEmail(user.getUsername()).isPresent()) {
			// encrypt password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		else {
			throw new DuplicateKeyException("Username is already in use");
		}
	}

	private void validate(User user){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<User>> result = validator.validate(user, Default.class);
		result.addAll(validator.validate(user, PasswordEncoder.class));
		if(result.size() > 0){
			throw new ConstraintViolationException(result);
		}
	}
}
