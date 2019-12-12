package com.microservice.account.service;


import com.microservice.account.dao.UserDao;
import com.microservice.account.dto.response.DefaultResponse;
import com.microservice.account.model.User;
import com.microservice.account.util.IdUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

/*
 * Created by dendy-prtha on 12/12/2019.
 * service layer for user
 */

@Service
@RequiredArgsConstructor
public class UserService {

    Logger log = Logger.getLogger(UserService.class.getSimpleName());

    private final UserDao userRepository;

    public DefaultResponse getAllUser() {
        try {
            return new DefaultResponse(true, "Found any user", userRepository.findAll());
        } catch (Exception e) {
            log.warning(e.getMessage());
        }

        return new DefaultResponse(false, "Data not found", null);
    }

    public DefaultResponse insertUser(User user) {
        user.setProviderId(IdUtility.generateUniqueID());
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return new DefaultResponse(false, "Email already Used!", null);
        } else {
            return new DefaultResponse(true, "Insert user success!", userRepository.save(user));
        }
    }

    public DefaultResponse updateUser(User user) {
        return new DefaultResponse(userRepository.save(user), "Insert user success!", null);
    }

    public DefaultResponse deleteUser(User user) {
        return new DefaultResponse(userRepository.delete(user), "delete user success!", null);
    }

    public DefaultResponse updateUserProfile(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            User currentUser = userOptional.get();
            currentUser.getUserProfile().setName(user.getUserProfile().getName());
            currentUser.getUserProfile().setDob(user.getUserProfile().getDob());
            currentUser.getUserProfile().setImageUrl(user.getUserProfile().getImageUrl());
            currentUser.getUserProfile().setPhone(user.getUserProfile().getPhone());
            return new DefaultResponse(true, "Update user profile success!", userRepository.update(user));
        } else {
            return new DefaultResponse(false, "User not found!", null);
        }
    }

    public DefaultResponse getUserProfileByUserId(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            return new DefaultResponse(true, "Update user profile success!", userOptional.get().getUserProfile());
        } else {
            return new DefaultResponse(false, "User not found!", null);
        }
    }
}
