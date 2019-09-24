package com.microservice.auth.dto.request;

import com.microservice.auth.dto.UserProfileDTO;
/*
 * Created by dendy-prtha on 17/09/2019.
 * delete user request
 */

public class DeleteUserRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
