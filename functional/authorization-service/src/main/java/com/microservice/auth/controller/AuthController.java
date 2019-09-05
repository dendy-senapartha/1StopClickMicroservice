package com.microservice.auth.controller;

import com.microservice.auth.domain.UserProfile;
import com.microservice.auth.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final UserProfileRepository userProfileRepository;

	@GetMapping("/info")
	public ResponseEntity<?> info(Principal principal) {
		if (principal != null) {
			Optional<UserProfile> user = userProfileRepository.findById(principal.getName());
			if (user.isPresent()) {
				Resource<UserProfile> resource = new Resource<>(user.get());
				resource.add(ControllerLinkBuilder.linkTo(AuthController.class)
						.slash("info").withSelfRel());
				return ResponseEntity.ok(resource);
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
}
