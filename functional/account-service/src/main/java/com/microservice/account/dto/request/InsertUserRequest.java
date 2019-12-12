package com.microservice.account.dto.request;

import com.microservice.account.model.UserProfile;
import lombok.Data;
/*
 * Created by dendy-prtha on 17/05/2019.
 * Create Order DTO
 * Draft, Issued, Paid, Void(https://www.replicon.com/help/setting-the-status-of-an-invoice/)
 */

@Data
public class InsertUserRequest {

    private String email;
    private Boolean emailVerified = false;
    private String password;
    private String provider;
    private String providerId;
    private UserProfile userProfile;
}
