package com.microservice.order.dto.request;

import lombok.Data;
/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

@Data
public class RemoveOrderItemFromOrderRequest {

    private String orderId;

    private OrderItem orderItem;

    @Data
    public static class OrderItem {
        private String productId;
        private String quantity;
    }

}
