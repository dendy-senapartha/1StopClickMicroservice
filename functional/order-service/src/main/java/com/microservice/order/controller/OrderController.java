package com.microservice.order.controller;

import com.microservice.order.dto.OrderDTO;
import com.microservice.order.dto.request.*;

import com.microservice.order.dto.response.DefaultResponse;

import com.microservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
/*
 * Created by dendy-prtha on 01/10/2019.
 * Order Controller
 */

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    @PostMapping(value = "/get-order-details-by-id")
    public ResponseEntity<?> getOrderDetailsById(@RequestBody GetOrderDetailsRequest request) {
        return ResponseEntity.ok(orderService.getOrderDetailsById(request.getOrderId()).getData());
    }

    @GetMapping(value = "/get-finished-order")
    public ResponseEntity<?> getUserOrder() {
        return ResponseEntity.ok(orderService.getUserOrder().getData());
    }

    @GetMapping(value = "/get-need-to-pay-order")
    public ResponseEntity<?> getUserOrderNeedToPay() {
        return ResponseEntity.ok(orderService.getUserOrderNeedToPay());
    }

    @PostMapping(value = "/add-item-to-order")
    public ResponseEntity<?> addItemToOrder(@RequestBody AddOrderItemToOrderRequest request)
            throws ParseException {

        DefaultResponse response = orderService.addItemToOrder(request.getProductId(), request.getCategory(), request.getQuantity());
        if (response.isSuccess()) {
            return ResponseEntity.ok(modelMapper.map(response.getData(), OrderDTO.class));
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping(value = "/remove-item-from-order")
    public ResponseEntity<?> removeItemFromOrder(@RequestBody RemoveOrderItemFromOrderRequest request)
            throws ParseException {
        DefaultResponse response = orderService.removeItemFromOrder(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping(value = "/delete-order-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOrdersById(@RequestBody GetByIdRequest request) {
        return ResponseEntity.ok(orderService.deleteOrderById(request.getId()));
    }
}
