package com.microservice.account.controller;

import com.microservice.account.dto.UserProfileDTO;
import com.microservice.account.dto.request.GetByUserId;
import com.microservice.account.dto.request.InsertUserRequest;
import com.microservice.account.dto.request.UpdateUserProfileRequest;
import com.microservice.account.dto.request.UpdateUserRequest;
import com.microservice.account.dto.response.DefaultResponse;
import com.microservice.account.model.User;
import com.microservice.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final UserService userService;

    @GetMapping("/user/get-all-user")
    public ResponseEntity<DefaultResponse> showListUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping(value = "/user/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> insertUser(@RequestBody InsertUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @PostMapping(value = "/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> updateUser(@RequestBody UpdateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping(value = "/user/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> deleteUser(@RequestBody GetByUserId request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.deleteUser(user));
    }

    @PostMapping(value = "/user/get-user-profile-by-userid",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> getProfileByUserId(@RequestBody GetByUserId request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.getUserProfileByUserId(user));
    }

    @PostMapping(value = "/user/update-user-profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.updateUserProfile(user));
    }
}