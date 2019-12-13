package com.microservice.auth.dto.request;

import com.microservice.auth.dto.UserProfileDTO;
import com.microservice.auth.model.Role;
import lombok.Data;

import java.util.Set;

/*
 * Created by dendy-prtha on 17/09/2019.
 *
 */
@Data
public class RegisterUserRequest {
    private Long id;
    private String email;
    private Boolean emailVerified;
    private String password;
    private String provider;
    private String providerId;
    private UserProfileDTO userProfile;
    private Set<Role> roles;

}
