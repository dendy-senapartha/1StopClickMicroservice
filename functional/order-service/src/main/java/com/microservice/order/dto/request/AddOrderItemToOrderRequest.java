package com.microservice.order.dto.request;

import lombok.Data;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

@Data
public class AddOrderItemToOrderRequest {

    private String productId;
    private String category;
    private String quantity;

}
