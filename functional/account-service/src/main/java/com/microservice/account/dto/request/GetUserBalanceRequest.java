package com.microservice.account.dto.request;

import lombok.Data;

/*
 * Created by dendy-prtha on 17/01/2020.
 * get user balance request
 */

@Data
public class GetUserBalanceRequest {
    private Long userId;
}
