package com.microservice.auth.controller;

import com.microservice.auth.dto.request.DeleteUserRequest;
import com.microservice.auth.dto.request.RegisterUserRequest;
import com.microservice.auth.dto.request.UpdateUserProfileRequest;
import com.microservice.auth.dto.request.UpdateUserRequest;
import com.microservice.auth.dto.response.DefaultResponse;
import com.microservice.auth.errors.ForbiddenException;
import com.microservice.auth.errors.InvalidPasswordException;
import com.microservice.auth.model.User;
import com.microservice.auth.service.UserService;
import com.microservice.auth.util.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<DefaultResponse> registerUser(@RequestBody RegisterUserRequest request) {
        if (!checkPasswordLength(request.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.RegisterUser(user));
    }

    @GetMapping(value = "/info")
    public ResponseEntity<?> getUserProfileByUserId(Principal principal) throws ForbiddenException {
        if (principal != null) {
            return ResponseEntity.ok(userService.getUserByEmail(principal.getName()).getData());
        }
        throw new ForbiddenException("Access forbidden", "invalid principal." ,"330103");
    }

    @PostMapping(value = "/update-user-profile")
    public ResponseEntity<DefaultResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Secured({AuthoritiesConstants.ADMIN})
    @GetMapping(value = "/admin/user-list")
    public ResponseEntity<DefaultResponse> showAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Secured({AuthoritiesConstants.ADMIN})
    @PostMapping(value = "/admin/update-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> updateUser(@RequestBody UpdateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Secured({AuthoritiesConstants.ADMIN})
    @PostMapping(value = "/admin/delete-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultResponse> deleteUser(@RequestBody DeleteUserRequest request) {
        return ResponseEntity.ok(userService.deleteUser(request.getId().toString()));
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= User.PASSWORD_MIN_LENGTH &&
                password.length() <= User.PASSWORD_MAX_LENGTH;
    }

}