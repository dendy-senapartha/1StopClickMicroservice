package com.microservice.order.dto.request;

import com.microservice.order.model.OrderItemCategory;
import lombok.Data;

import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * Create Order DTO
 * Draft, Issued, Paid, Void(https://www.replicon.com/help/setting-the-status-of-an-invoice/)
 */
@Data
public class CreateOrderRequest {

    private String userId;

    private List<OrderItem> orderItemList;

    private String paymentMethodId;

    private String invoiceStatus;

    private String description;

    private String OrderDate;

    @Data
    public static class OrderItem {
        private String productId;
        private String quantity;
        private String subtotal;
        private OrderItemCategory category;
    }

}
