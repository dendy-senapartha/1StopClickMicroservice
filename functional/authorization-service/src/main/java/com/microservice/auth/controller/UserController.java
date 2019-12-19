package com.microservice.auth.controller;

import com.microservice.auth.dto.request.DeleteUserRequest;
import com.microservice.auth.dto.request.RegisterUserRequest;
import com.microservice.auth.dto.request.UpdateUserProfileRequest;
import com.microservice.auth.dto.request.UpdateUserRequest;
import com.microservice.auth.dto.response.DefaultResponse;
import com.microservice.auth.model.User;
import com.microservice.auth.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User REST Controller for user
 */

@RestController
@RequiredArgsConstructor
public class UserController {

    public static final String SUCCESS = "success";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<DefaultResponse> registerUser(@RequestBody RegisterUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.RegisterUser(user));
    }

    @GetMapping(value = "/info")
    public ResponseEntity<?> getUserProfileByUserId(Principal principal) {
        if (principal != null) {
            return ResponseEntity.ok(userService.getUserByEmail(principal.getName()).getData());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping(value = "/update-user-profile")
    public ResponseEntity<DefaultResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Secured({ROLE_ADMIN})
    @GetMapping(value = "/admin/user-list")
    public ResponseEntity<DefaultResponse> showAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Secured({ROLE_ADMIN})
    @PostMapping(value = "/admin/update-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> updateUser(@RequestBody UpdateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Secured({ROLE_ADMIN})
    @PostMapping(value = "/admin/delete-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> deleteUser(@RequestBody DeleteUserRequest request) {
        return ResponseEntity.ok(userService.deleteUser(request.getId().toString()));
    }

}