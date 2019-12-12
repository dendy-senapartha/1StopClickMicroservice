package com.microservice.account.dto.request;

import lombok.Data;
/*
 * Created by dendy-prtha on 17/05/2019.
 * Create Order DTO
 * Draft, Issued, Paid, Void(https://www.replicon.com/help/setting-the-status-of-an-invoice/)
 */

@Data
public class UpdateUserProfileRequest {

    private Long userId;

    private String name;

    private String dob;

    private String phone;

    private String imageUrl;
}
