package com.microservice.order.controller;


import com.microservice.order.dto.InvoiceDTO;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.dto.OrderItemDTO;
import com.microservice.order.dto.PaymentMethodDTO;
import com.microservice.order.dto.request.AddOrderItemToOrderRequest;
import com.microservice.order.dto.request.GetMovieByIdRequest;
import com.microservice.order.dto.request.GetOrderDetailsRequest;

import com.microservice.order.model.OrderItem;
import com.microservice.order.model.Orders;
import com.microservice.order.model.external.Movie;
import com.microservice.order.model.external.User;
import com.microservice.order.repository.OrderRepository;
import com.microservice.order.repository.external.AuthServiceClient;
import com.microservice.order.repository.external.MovieServiceClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

/*
 * Created by dendy-prtha on 01/10/2019.
 * Order Controller
 */

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    private final AuthServiceClient authServiceClient;

    private final MovieServiceClient movieServiceClient;

    private Converter<Orders, OrderDTO> orderConverter = new Converter<Orders, OrderDTO>() {
        @Override
        public OrderDTO convert(MappingContext<Orders, OrderDTO> mappingContext) {
            Orders source = mappingContext.getSource();
            OrderDTO destination = new OrderDTO();
            destination.setId(source.getId());
            destination.setOrderDate(source.getOrderDate());
            destination.setTotalAmount(source.getTotalAmount());

            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setId(source.getInvoice().getId());
            invoiceDTO.setCreated(source.getInvoice().getCreated());
            invoiceDTO.setDescription(source.getInvoice().getDescription());
            invoiceDTO.setStatus(source.getInvoice().getStatus());

            PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
            paymentMethodDTO.setId(source.getInvoice().getPaymentMethod().getId());
            paymentMethodDTO.setCode(source.getInvoice().getPaymentMethod().getCode());
            paymentMethodDTO.setName(source.getInvoice().getPaymentMethod().getName());
            invoiceDTO.setPaymentMethod(paymentMethodDTO);

            List<OrderItemDTO> orderItemList = new ArrayList<>();
            for (OrderItem orderItem : source.getOrderItemList()) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setProductId(orderItem.getProductId() + "");
                orderItemDTO.setQuantity(orderItem.getQuantity() + "");
                orderItemDTO.setCategory(orderItem.getCategory().getName());

                GetMovieByIdRequest movieByIdRequest = new GetMovieByIdRequest();
                movieByIdRequest.setId(orderItem.getProductId() + "");
                Movie movie = movieServiceClient.getMovieById(movieByIdRequest).getBody();

                orderItemDTO.setProductName(movie.getProductName());
                orderItemDTO.setSubtotal(orderItem.getSubtotal() + "");

                orderItemList.add(orderItemDTO);
            }
            destination.setOrderItemList(orderItemList);
            destination.setInvoice(invoiceDTO);

            destination.setInvoice(invoiceDTO);
            return destination;
        }
    };

    @PostMapping(value = "/add-item-to-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOrder(@RequestBody AddOrderItemToOrderRequest request) {

        return ResponseEntity.ok("testing njing");
    }

    @PostMapping(value = "/get-order-details-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderDetailsById(@RequestBody GetOrderDetailsRequest request) {
        OrderDTO result = new OrderDTO();
        Optional orders = orderRepository.findById(request.getOrderId());
        if (orders.isPresent()) {
            modelMapper.addConverter(orderConverter);
            result = modelMapper.map(orders.get(), OrderDTO.class);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/get-finished-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserOrder() {

        User user = authServiceClient.getUser().getBody();
        List<Orders> ordersList = orderRepository.getFinishedOrderByUserId(user.getId());
        modelMapper.addConverter(orderConverter);
        return ResponseEntity.ok(ordersList.stream().map(orders -> modelMapper.map(orders, OrderDTO.class)));
    }

    @GetMapping(value = "/get-need-to-pay-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserOrderNeedToPay() {

        User user = authServiceClient.getUser().getBody();
        List<Orders> ordersList = orderRepository.getUserOrderNeedTooPay(user.getId());
        modelMapper.addConverter(orderConverter);
        return ResponseEntity.ok(ordersList.stream().map(orders -> modelMapper.map(orders, OrderDTO.class)));
    }
}
