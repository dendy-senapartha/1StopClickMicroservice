package com.microservice.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 */
@Service
public class AuthenticationService {

	public Optional<Authentication> get(){
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

	public Optional<String> getUsername(){
		String name = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			name = authentication.getName();
		}
		return Optional.ofNullable(name);
	}
}
