package com.microservice.auth.service;

import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 *
 */
public interface AuthenticationService {
	Optional<Authentication> get();

	Optional<String> getUsername();
}
