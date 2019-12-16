package com.microservice.auth.controller;

import com.microservice.auth.dto.UserDTO;
import com.microservice.auth.model.User;
import com.microservice.auth.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    //https://www.devglan.com/spring-security/spring-oauth2-role-based-authorization
    @GetMapping("/info")
    public ResponseEntity<?> info(Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok(userService.getUserByEmail(principal.getName()));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
