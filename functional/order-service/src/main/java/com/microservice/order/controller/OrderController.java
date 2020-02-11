package com.microservice.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.dto.request.*;

import com.microservice.order.dto.response.DefaultResponse;

import com.microservice.order.model.external.User;
import com.microservice.order.service.AuthServiceClient;
import com.microservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
/*
 * Created by dendy-prtha on 01/10/2019.
 * Order Controller
 */

@RestController
@RequiredArgsConstructor
public class OrderController  {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    private final AuthServiceClient authServiceClient;

    @PostMapping(value = "/get-order-details-by-id")
    public ResponseEntity<?> getOrderDetailsById(@RequestBody GetOrderDetailsRequest request) {
        return ResponseEntity.ok(orderService.getOrderDetailsById(request.getOrderId()).getData());
    }

    @GetMapping(value = "/get-finished-order")
    public ResponseEntity<?> getUserOrder() throws IOException {
        return ResponseEntity.ok(orderService.getUserOrder().getData());
    }

    @GetMapping(value = "/get-need-to-pay-order")
    public ResponseEntity<?> getUserOrderNeedToPay() throws IOException {
        return ResponseEntity.ok(orderService.getUserOrderNeedToPay());
    }

    @PostMapping(value = "/add-item-to-order")
    public ResponseEntity<?> addItemToOrder(@RequestBody AddOrderItemToOrderRequest request)
            throws ParseException, IOException {

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

    @PostMapping(value = "/test-request",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testKafkaRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            User user = new ObjectMapper().readValue(
                    authServiceClient.getUser(authentication.getPrincipal().toString()).toString(), User.class);
            return ResponseEntity.ok(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("fail");
    }
}
