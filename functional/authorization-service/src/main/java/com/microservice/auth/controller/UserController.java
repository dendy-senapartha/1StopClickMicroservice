package com.microservice.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.microservice.auth.dao.RoleDao;
import com.microservice.auth.dao.UserDao;
import com.microservice.auth.dto.UserDTO;
import com.microservice.auth.dto.UserProfileDTO;
import com.microservice.auth.dto.request.DeleteUserRequest;
import com.microservice.auth.dto.request.RegisterUserRequest;
import com.microservice.auth.dto.request.UpdateUserProfileRequest;
import com.microservice.auth.dto.request.UpdateUserRequest;
import com.microservice.auth.dto.response.BaseResponse;
import com.microservice.auth.model.Role;
import com.microservice.auth.model.User;
import com.microservice.auth.model.UserProfile;
import com.microservice.auth.util.IdUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

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

    private final UserDao userRepository;

    private final RoleDao roleRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse registerUser(@RequestBody RegisterUserRequest request) {
        String email = request.getEmail();
        boolean emailVerified = false;
        String password = passwordEncoder.encode(request.getPassword());
        String provider = request.getProvider();
        String provider_id = IdUtility.generateUniqueID();
        ObjectMapper objectMapper = new ObjectMapper();
        Object usrPrflObject = request.getUserProfile();
        UserProfile usrPrfl = objectMapper.convertValue(usrPrflObject, UserProfile.class);
        User user = new User(null, email, emailVerified, password, provider, provider_id);
        user.addUserProfile(new UserProfile(null, usrPrfl.getName(),
                usrPrfl.getDob(), usrPrfl.getPhone(), usrPrfl.getImageUrl()));

        for (Role requestedRole : request.getRoles()) {
            Optional<Role> roleOptional = roleRepository.findById(requestedRole.getId());
            if (roleOptional.isPresent()) {
                user.addRole(roleOptional.get());
            }
        }

        BaseResponse response = new BaseResponse();
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
        if (principal != null) {
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
    @ResponseBody
    public ResponseEntity<?> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        BaseResponse response = new BaseResponse();

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
        Resource<BaseResponse> resource = new Resource<>(response);
        return ResponseEntity.ok(resource);
    }

    @Secured({ROLE_ADMIN})
    @GetMapping(value = "/admin/user-list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> showAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(modelMapper.map(user, UserDTO.class));
        }
        return ResponseEntity.ok(usersDTO);
    }

    @Secured({ROLE_ADMIN})
    @PostMapping(value = "/admin/update-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request) {
        BaseResponse response = new BaseResponse();
        Optional<User> userOptional = userRepository.findById(request.getId());
        User currentUser = null;
        if (userOptional.isPresent()) {
            currentUser = userOptional.get();
            currentUser.setEmail(request.getEmail());
            currentUser.setPassword(passwordEncoder.encode(request.getPassword()));
            currentUser.setProvider(request.getProvider());
            currentUser.setProviderId(request.getProviderId());
            currentUser.getUserProfile().setName(request.getUserProfile().getName());
            currentUser.getUserProfile().setDob(request.getUserProfile().getDob());
            currentUser.getUserProfile().setPhone(request.getUserProfile().getPhone());
            currentUser.getUserProfile().setImageUrl(request.getUserProfile().getImage_url());

            //update the role
            currentUser.removeAllRole();
            for (Role requestedRole : request.getRoles()) {
                Optional<Role> roleOptional = roleRepository.findById(requestedRole.getId());
                if (roleOptional.isPresent()) {
                    currentUser.addRole(roleOptional.get());
                }
            }
        }
        response.setStatus(userRepository.update(currentUser) + "");
        return ResponseEntity.ok(response);
    }

    @Secured({ROLE_ADMIN})
    @PostMapping(value = "/admin/delete-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request) {
        User delUser = userRepository.findById(request.getId()).get();
        BaseResponse response = new BaseResponse();
        response.setStatus(userRepository.delete(delUser) + "");
        return ResponseEntity.ok(response);
    }

}