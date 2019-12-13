package com.microservice.auth.dto.request;

import com.microservice.auth.dto.UserProfileDTO;
import lombok.Data;
/*
 * Created by dendy-prtha on 17/09/2019.
 * delete user request
 */

@Data
public class DeleteUserRequest {
    private Long id;
}
