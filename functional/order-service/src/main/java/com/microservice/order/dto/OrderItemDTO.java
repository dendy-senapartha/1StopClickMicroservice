package com.microservice.order.dto;

import lombok.Data;

/*
 * Created by dendy-prtha on 01/07/2019.
 * order item DTO
 */
@Data
public class OrderItemDTO {
    private String productId;
    private String productName;
    private String category;
    private String quantity;
    private String subtotal;

}
