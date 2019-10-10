package com.microservice.order.dto;

import lombok.Data;

/*
 * Created by dendy-prtha on 01/07/2019.
 * payment method DTO
 */
@Data
public class PaymentMethodDTO {
    private int id;

    private String code;

    private String name;

}
