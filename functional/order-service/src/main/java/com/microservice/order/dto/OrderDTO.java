package com.microservice.order.dto;

import com.microservice.order.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * Created by dendy-prtha on 01/07/2019.
 * order  DTO
 */

@Data
public class OrderDTO {
    private int id;

    private Date orderDate;

    private BigDecimal totalAmount;

    private List<OrderItemDTO> orderItemList = new ArrayList<>();

    private InvoiceDTO invoice;

}
