package com.microservice.auth.dto;

import lombok.Data;

import java.util.Set;
/*
 * Created by dendy-prtha on 01/03/2019.
 * User DTO
 */
@Data
public class UserDTO{
    private Long id;

    private String email;

    private Boolean emailVerified = false;

    private String password;

    private String provider;

    private String providerId;

    private UserProfileDTO userProfile;

    private Set<RoleDTO> roles;
}
