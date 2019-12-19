package com.microservice.order.dto.request;


import com.microservice.order.dto.InvoiceDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/*
 * Created by dendy-prtha on 01/07/2019.
 * get order details  DTO
 */
@Data
public class GetOrderDetailsRequest {
    private int orderId;
}
