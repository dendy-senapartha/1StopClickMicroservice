package com.microservice.order.dto;

import lombok.Data;

import java.util.Date;
/*
 * Created by dendy-prtha on 01/07/2019.
 * order  DTO
 */

@Data
public class InvoiceDTO {

    private int id;

    private PaymentMethodDTO paymentMethod;

    private String description;

    private String status;

    private Date created;
}
