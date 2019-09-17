package com.microservice.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.microservice.auth.dao.UserDao;
import com.microservice.auth.dto.UserProfileDTO;
import com.microservice.auth.dto.request.RegisterUserRequest;
import com.microservice.auth.dto.request.UpdateUserProfileRequest;
import com.microservice.auth.dto.response.RegisterUserResponse;
import com.microservice.auth.dto.response.UpdateUserProfileResponse;
import com.microservice.auth.model.User;
import com.microservice.auth.model.UserProfile;
import com.microservice.auth.util.IdUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User REST Controller for user
 */

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDao userRepository;

    private final ModelMapper modelMapper;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RegisterUserResponse insertUser(@RequestBody RegisterUserRequest request) {
        String email = request.getEmail();
        boolean emailVerified = false;
        String password = request.getPassword();
        String provider = request.getProvider();
        String provider_id = IdUtility.generateUniqueID();
        ObjectMapper objectMapper = new ObjectMapper();
        Object usrPrflObject = request.getUserProfile();
        UserProfile usrPrfl = objectMapper.convertValue(usrPrflObject, UserProfile.class);
        User user = new User(null, email, emailVerified, password, provider, provider_id);
        user.addUserProfile(new UserProfile(null, usrPrfl.getName(),
                usrPrfl.getDob(), usrPrfl.getPhone(), usrPrfl.getImageUrl()));
        RegisterUserResponse response = new RegisterUserResponse();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            response.setStatus("" + false);
            response.setMessage("Email already Used!");

        } else {
            response.setStatus("" + userRepository.save(user));
            response.setMessage("");
        }
        return response;
    }

    @GetMapping(value = "/get-user-profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserProfileByUserId(Principal principal) {
        if(principal != null)
        {
            UserProfileDTO response = new UserProfileDTO();
            Optional<User> userOptional = userRepository.findByEmail(principal.getName());
            User currentUser;
            if (userOptional.isPresent()) {
                currentUser = userOptional.get();
                response.setId(currentUser.getUserProfile().getId());
                response.setName(currentUser.getUserProfile().getName());
                response.setDob(currentUser.getUserProfile().getDob());
                response.setImage_url(currentUser.getUserProfile().getImageUrl());
                response.setPhone(currentUser.getUserProfile().getPhone());
            }
            Resource<UserProfileDTO> resource = new Resource<>(response);
            return ResponseEntity.ok(resource);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @PostMapping(value = "/update-user-profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        UpdateUserProfileResponse response = new UpdateUserProfileResponse();

        Optional<User> userOptional = userRepository.findById(request.getUserId());
        User currentUser = null;
        if (userOptional.isPresent()) {
            currentUser = userOptional.get();
            currentUser.getUserProfile().setName(request.getName());
            currentUser.getUserProfile().setDob(request.getDob());
            currentUser.getUserProfile().setImageUrl(request.getImageUrl());
            currentUser.getUserProfile().setPhone(request.getPhone());
        }
        response.setStatus("" + userRepository.update(currentUser));
        Resource<UpdateUserProfileResponse> resource = new Resource<>(response);
        return ResponseEntity.ok(resource);
    }

}