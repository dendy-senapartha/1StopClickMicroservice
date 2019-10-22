package com.microservice.order.dto.request;


import com.microservice.order.dto.InvoiceDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/*
 * Created by dendy-prtha on 01/07/2019.
 * get order details  DTO
 */

public class GetOrderDetailsRequest {
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
