package com.microservice.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public Optional<Authentication> get(){
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
	}

	@Override
	public Optional<String> getUsername(){
		String name = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			name = authentication.getName();
		}
		return Optional.ofNullable(name);
	}
}
