package com.microservice.order.controller;


import com.microservice.order.ApiError;
import com.microservice.order.dto.InvoiceDTO;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.dto.OrderItemDTO;
import com.microservice.order.dto.PaymentMethodDTO;
import com.microservice.order.dto.request.*;

import com.microservice.order.dto.response.AddOrderItemToOrderResponse;
import com.microservice.order.dto.response.RemoveOrderItemFromOrderResponse;
import com.microservice.order.model.*;
import com.microservice.order.model.external.Movie;
import com.microservice.order.model.external.Song;
import com.microservice.order.model.external.User;
import com.microservice.order.repository.dao.OrderDao;
import com.microservice.order.repository.dao.OrderItemCategoryDao;
import com.microservice.order.repository.dao.PaymentMethodDao;
import com.microservice.order.repository.external.AuthServiceClient;
import com.microservice.order.repository.external.MovieServiceClient;
import com.microservice.order.repository.external.MusicServiceClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by dendy-prtha on 01/10/2019.
 * Order Controller
 */

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderDao orderRepository;

    private final PaymentMethodDao paymentMethodRepository;

    private final OrderItemCategoryDao orderItemCategoryRepository;

    private final ModelMapper modelMapper;

    private final AuthServiceClient authServiceClient;

    private final MovieServiceClient movieServiceClient;

    private final MusicServiceClient musicServiceClient;

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

                GetByIdRequest getByIdRequest = new GetByIdRequest();
                if (orderItem.getCategory().getName().equalsIgnoreCase("MOVIES")) {
                    getByIdRequest.setId(orderItem.getProductId() + "");
                    Movie movie = movieServiceClient.getMovieById(getByIdRequest).getBody();
                    orderItemDTO.setProductName(movie.getProductName());
                } else if (orderItem.getCategory().getName().equalsIgnoreCase("MUSICS")) {
                    getByIdRequest.setId(orderItem.getProductId() + "");
                    Song song = musicServiceClient.getMusicById(getByIdRequest).getBody();
                    orderItemDTO.setProductName(song.getProductName());
                }

                orderItemDTO.setSubtotal(orderItem.getSubtotal() + "");

                orderItemList.add(orderItemDTO);
            }
            destination.setOrderItemList(orderItemList);
            destination.setInvoice(invoiceDTO);

            destination.setInvoice(invoiceDTO);
            return destination;
        }
    };

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

    public boolean createOrder(CreateOrderRequest createOrderRequest) throws ParseException {

        Date orderDate = new SimpleDateFormat("dd-MM-yyyy").parse(createOrderRequest.getOrderDate());
        int paymentMethodId = Integer.parseInt(createOrderRequest.getPaymentMethodId());
        String invoiceStatus = createOrderRequest.getInvoiceStatus();
        String description = createOrderRequest.getDescription();

        List<CreateOrderRequest.OrderItem> orderItemList = new ArrayList<>(createOrderRequest.getOrderItemList());

        Map<String, String> result = new HashMap<>();
        Orders order = new Orders();
        BigDecimal totalAmount = new BigDecimal(0);

        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(paymentMethodId);
        PaymentMethod paymentMethod = null;
        if (paymentMethodOptional.isPresent()) {
            paymentMethod = paymentMethodOptional.get();
        }

        for (CreateOrderRequest.OrderItem orderItemDTO : orderItemList) {
            int quantity = Integer.parseInt(orderItemDTO.getQuantity());
            BigDecimal subtotal = new BigDecimal(orderItemDTO.getSubtotal());

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(quantity);
            orderItem.setSubtotal(subtotal);
            orderItem.setProductId(Integer.parseInt(orderItemDTO.getProductId()));
            orderItem.setCategory(orderItemDTO.getCategory());

            totalAmount = totalAmount.add(subtotal);
            order.addOrderItem(orderItem);
        }

        Invoice invoice = new Invoice();
        invoice.setPaymentMethod(paymentMethod);
        invoice.setUserId(Long.parseLong(createOrderRequest.getUserId()));
        invoice.setStatus(invoiceStatus);
        invoice.setDescription(description);
        invoice.setCreated(orderDate);

        order.addInvoice(invoice);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(orderDate);

        return orderRepository.save(order);
    }

    @PostMapping(value = "/add-item-to-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addItemToOrder(@RequestBody AddOrderItemToOrderRequest orderItem)
            throws ParseException {
        AddOrderItemToOrderResponse response = new AddOrderItemToOrderResponse();

        int quantity = Integer.parseInt(orderItem.getQuantity());
        User user = authServiceClient.getUser().getBody();
        BigDecimal subtotal = null;

        OrderItemCategory category = orderItemCategoryRepository.findByName(orderItem.getCategory());
        if (category == null) {
            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, "categorinya salah cuk");
            return new ResponseEntity<Object>(
                    apiError, new HttpHeaders(), apiError.getStatus());
        }
        //fill order detail item with correct data
        GetByIdRequest getByIdRequest = new GetByIdRequest();
        if (category.getName().equalsIgnoreCase("MOVIES")) {
            getByIdRequest.setId(orderItem.getProductId() + "");
            Movie movie = movieServiceClient.getMovieById(getByIdRequest).getBody();
            subtotal = movie.getPrice().multiply(new BigDecimal(quantity));

        } else if (category.getName().equalsIgnoreCase("MUSICS")) {
            getByIdRequest.setId(orderItem.getProductId() + "");
            Song song = musicServiceClient.getMusicById(getByIdRequest).getBody();
            subtotal = song.getPrice().multiply(new BigDecimal(quantity));
        }

        //find if there any draft order
        List<Orders> draftOrder = orderRepository.findUserDraftOrder(user.getId());
        if (!draftOrder.isEmpty()) {
            OrderItem orderItemToAdd = new OrderItem();
            orderItemToAdd.setQuantity(quantity);
            orderItemToAdd.setSubtotal(subtotal);
            orderItemToAdd.setProductId(Integer.parseInt(orderItem.getProductId()));
            orderItemToAdd.setCategory(new OrderItemCategory());
            orderItemToAdd.getCategory().setId(category.getId());
            orderItemToAdd.getCategory().setCreated(category.getCreated());
            orderItemToAdd.getCategory().setName(category.getName());

            //make sure there is only one order that has DRAFT status
            if (draftOrder.size() == 1) {
                for (Orders currentOrder : draftOrder) {
                    BigDecimal newTotalAmount = BigDecimal.ZERO;
                    for (OrderItem currentOrderItem : currentOrder.getOrderItemList()) {
                        newTotalAmount = newTotalAmount.add(currentOrderItem.getSubtotal());
                    }
                    currentOrder.setTotalAmount(newTotalAmount.add(subtotal));
                    currentOrder.addOrderItem(orderItemToAdd);
                    response.setStatus(orderRepository.update(currentOrder) + "");
                    response.setItemId(orderItemToAdd.getProductId() + "");
                }
            }
        }
        //no draft order before.. so create one
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
            createOrderRequest.setOrderDate(sdf.format(Calendar.getInstance().getTime()));
            createOrderRequest.setInvoiceStatus("DRAFT");
            createOrderRequest.setDescription("");
            createOrderRequest.setPaymentMethodId("1");
            createOrderRequest.setUserId("" + user.getId());

            List<CreateOrderRequest.OrderItem> orderItems = new ArrayList<>();
            CreateOrderRequest.OrderItem itemToAdd = new CreateOrderRequest.OrderItem();
            itemToAdd.setProductId(orderItem.getProductId());
            itemToAdd.setQuantity(orderItem.getQuantity());
            itemToAdd.setSubtotal("" + subtotal);
            itemToAdd.setCategory(new OrderItemCategory());
            itemToAdd.getCategory().setId(category.getId());
            itemToAdd.getCategory().setCreated(category.getCreated());
            itemToAdd.getCategory().setName(category.getName());
            orderItems.add(itemToAdd);

            createOrderRequest.setOrderItemList(orderItems);
            response.setStatus(createOrder(createOrderRequest) + "");
            response.setItemId(itemToAdd.getProductId() + "");
        }
        return ResponseEntity.ok(modelMapper.map(response, AddOrderItemToOrderResponse.class));
    }

    @PostMapping(value = "/remove-item-from-order",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeItemFromOrder(@RequestBody RemoveOrderItemFromOrderRequest orderItem)
            throws ParseException {
        RemoveOrderItemFromOrderResponse response = new RemoveOrderItemFromOrderResponse();

        RemoveOrderItemFromOrderRequest.OrderItem theItem = orderItem.getOrderItem();
        int productId = Integer.parseInt(theItem.getProductId());
        int orderId = Integer.parseInt(orderItem.getOrderId());

        Optional<Orders> ordersOptional = orderRepository.findById(orderId);
        Orders currentOrder;
        if (ordersOptional.isPresent()) {
            currentOrder = ordersOptional.get();

            for (Iterator<OrderItem> orderItemIterator = currentOrder.getOrderItemList().iterator();
                 orderItemIterator.hasNext(); ) {
                OrderItem orderedItem = orderItemIterator.next();
                if (productId == orderedItem.getProductId()) {
                    BigDecimal total = currentOrder.getTotalAmount().subtract(orderedItem.getSubtotal());
                    currentOrder.setTotalAmount(total);
                    orderItemIterator.remove();
                }
            }

            response.setStatus(orderRepository.update(currentOrder) + "");
            response.setItemId(productId + "");

            //if orderitem is zero, then remove the order
            if (currentOrder.getOrderItemList().isEmpty()) {
                response.setStatus(orderRepository.delete(currentOrder) + "");
            }
        }
        return ResponseEntity.ok(modelMapper.map(response, RemoveOrderItemFromOrderResponse.class));
    }

    @PostMapping(value = "/delete-order-by-id",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOrdersById(@RequestBody GetByIdRequest request) {

        Optional<Orders> ordersOptional = orderRepository.findById(Integer.valueOf(request.getId()));
        if (ordersOptional.isPresent()) {
            Orders currentOrder = ordersOptional.get();

            return ResponseEntity.ok(orderRepository.delete(currentOrder));
        } else {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Order ID Not Found");
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }


    }
}
