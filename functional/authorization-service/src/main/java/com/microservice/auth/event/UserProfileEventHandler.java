package com.microservice.auth.event;

import com.microservice.auth.domain.User;
import com.microservice.auth.domain.UserProfile;
import com.microservice.auth.repository.UserRepository;
import com.microservice.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class UserProfileEventHandler {
	private final UserRepository userRepository;
	private final AuthenticationService authenticationService;

	@HandleBeforeCreate
	public void handleBeforeCreate(UserProfile profile) {
		authenticationService.getUsername().ifPresent(username->{
			Optional<User> user = userRepository.findById(username);
			user.ifPresent(profile::setUser);
		});
	}
}
