package com.microservice.account.dto;

import lombok.Data;

/*
 * Created by dendy-prtha on 17/05/2019.
 * Track type DTO
 */

@Data
public class UserProfileDTO {

    private Long id;

    private String name;

    private String dob;

    private String phone;

    private String imageUrl;
}
