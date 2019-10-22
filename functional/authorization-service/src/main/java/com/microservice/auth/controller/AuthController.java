package com.microservice.auth.controller;

import com.microservice.auth.dao.repository.UserRepository;
import com.microservice.auth.dto.UserDTO;
import com.microservice.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
	private final UserRepository userRepository;

	private final ModelMapper modelMapper;
//https://www.devglan.com/spring-security/spring-oauth2-role-based-authorization
	@GetMapping("/info")
	public ResponseEntity<?> info(Principal principal) {
		if (principal != null) {
			Optional<User> user = userRepository.findByEmail(principal.getName());
			if (user.isPresent()) {
                UserDTO dto = modelMapper.map(user.get(), UserDTO.class);
				//Resource<UserDTO> resource = new Resource<>(dto);
				//resource.add(ControllerLinkBuilder.linkTo(AuthController.class)
				//		.slash("info").withSelfRel());
				return ResponseEntity.ok(dto);
			}
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
