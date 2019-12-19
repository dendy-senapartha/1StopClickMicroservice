package com.microservice.order.dto.response;

import lombok.Data;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */
@Data
public class AddOrderItemToOrderResponse {

    private String status;

    private String itemId;
}
