package com.microservice.order.service;

import com.microservice.order.dto.InvoiceDTO;
import com.microservice.order.dto.OrderDTO;
import com.microservice.order.dto.OrderItemDTO;
import com.microservice.order.dto.PaymentMethodDTO;
import com.microservice.order.dto.request.CreateOrderRequest;
import com.microservice.order.dto.request.GetByIdRequest;
import com.microservice.order.dto.request.RemoveOrderItemFromOrderRequest;
import com.microservice.order.dto.response.DefaultResponse;
import com.microservice.order.model.*;
import com.microservice.order.model.external.Movie;
import com.microservice.order.model.external.Song;
import com.microservice.order.model.external.User;
import com.microservice.order.repository.OrderItemCategoryRepository;
import com.microservice.order.repository.OrderRepository;
import com.microservice.order.repository.PaymentMethodRepository;
import com.microservice.order.repository.external.AuthServiceClient;
import com.microservice.order.repository.external.MovieServiceClient;
import com.microservice.order.repository.external.MusicServiceClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by dendy-prtha on 17/12/2019.
 * service component for order
 */

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    private final OrderItemCategoryRepository orderItemCategoryRepository;

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

    public DefaultResponse getOrderDetailsById(int orderId) {
        OrderDTO result = new OrderDTO();
        Optional orders = orderRepository.findById(orderId);
        if (orders.isPresent()) {
            modelMapper.addConverter(orderConverter);
            result = modelMapper.map(orders.get(), OrderDTO.class);
        }
        return new DefaultResponse(true, "", result);
    }

    public DefaultResponse getUserOrder() {
        User user = authServiceClient.getUser().getBody();
        List<Orders> ordersList = orderRepository.getFinishedOrderByUserId(user.getId());
        modelMapper.addConverter(orderConverter);

        return new DefaultResponse(true, "",
                ordersList.stream().map(orders -> modelMapper.map(orders, OrderDTO.class)));
    }

    public DefaultResponse getUserOrderNeedToPay() {
        User user = authServiceClient.getUser().getBody();
        List<Orders> ordersList = orderRepository.getUserOrderNeedTooPay(user.getId());
        modelMapper.addConverter(orderConverter);

        return new DefaultResponse(true, "",
                ordersList.stream().map(orders -> modelMapper.map(orders, OrderDTO.class)));
    }

    public Orders createOrder(CreateOrderRequest createOrderRequest) throws ParseException {

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

    public DefaultResponse addItemToOrder(String productId, String category, String quantity)
            throws ParseException {
        int qty = Integer.parseInt(quantity);
        User user = authServiceClient.getUser().getBody();
        BigDecimal subtotal = null;

        OrderItemCategory ctgry = orderItemCategoryRepository.findByName(category);
        if (ctgry == null) {
            return new DefaultResponse(false, "Category not found!", null);
        }
        //fill order detail item with correct data
        GetByIdRequest getByIdRequest = new GetByIdRequest();
        if (ctgry.getName().equalsIgnoreCase("MOVIES")) {
            getByIdRequest.setId(productId);
            Movie movie = movieServiceClient.getMovieById(getByIdRequest).getBody();
            subtotal = movie.getPrice().multiply(new BigDecimal(qty));

        } else if (ctgry.getName().equalsIgnoreCase("MUSICS")) {
            getByIdRequest.setId(productId);
            Song song = musicServiceClient.getMusicById(getByIdRequest).getBody();
            subtotal = song.getPrice().multiply(new BigDecimal(qty));
        }

        //find if there any draft order
        List<Orders> draftOrder = orderRepository.findUserDraftOrder(user.getId());
        if (!draftOrder.isEmpty()) {
            OrderItem orderItemToAdd = new OrderItem();
            orderItemToAdd.setQuantity(qty);
            orderItemToAdd.setSubtotal(subtotal);
            orderItemToAdd.setProductId(Integer.parseInt(productId));
            orderItemToAdd.setCategory(new OrderItemCategory());
            orderItemToAdd.getCategory().setId(ctgry.getId());
            orderItemToAdd.getCategory().setCreated(ctgry.getCreated());
            orderItemToAdd.getCategory().setName(ctgry.getName());

            //make sure there is only one order that has DRAFT status
            if (draftOrder.size() == 1) {

                Orders currentOrder = draftOrder.get(0);
                BigDecimal newTotalAmount = BigDecimal.ZERO;
                for (OrderItem currentOrderItem : currentOrder.getOrderItemList()) {
                    newTotalAmount = newTotalAmount.add(currentOrderItem.getSubtotal());
                }
                currentOrder.setTotalAmount(newTotalAmount.add(subtotal));
                currentOrder.addOrderItem(orderItemToAdd);

                return new DefaultResponse(true, "", orderRepository.save(currentOrder));
            } else {
                return new DefaultResponse(false, "Failing when save on existing order", null);
            }
        }
        //no prev draft order.. so create new one
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
            itemToAdd.setProductId(productId);
            itemToAdd.setQuantity(qty + "");
            itemToAdd.setSubtotal("" + subtotal);
            itemToAdd.setCategory(new OrderItemCategory());
            itemToAdd.getCategory().setId(ctgry.getId());
            itemToAdd.getCategory().setCreated(ctgry.getCreated());
            itemToAdd.getCategory().setName(ctgry.getName());
            orderItems.add(itemToAdd);
            createOrderRequest.setOrderItemList(orderItems);

            return new DefaultResponse(true, "", createOrder(createOrderRequest));
        }
    }

    public DefaultResponse removeItemFromOrder(RemoveOrderItemFromOrderRequest orderItem) {
        DefaultResponse response = new DefaultResponse();
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
            response.setData(orderRepository.save(currentOrder));
            response.setMessage("Deleting item succeed");
            response.setSuccess(true);
            //if orderitem is zero, then remove the order
            if (currentOrder.getOrderItemList().isEmpty()) {
                orderRepository.delete(currentOrder);
                response.setMessage("Deleting item and order succeed");
                response.setData(null);
            }
        } else {
            response.setData(null);
            response.setMessage("Deleting item fail");
            response.setSuccess(false);
        }
        return response;
    }

    public DefaultResponse deleteOrderById(String orderId) {
        Optional<Orders> ordersOptional = orderRepository.findById(Integer.valueOf(orderId));
        if (ordersOptional.isPresent()) {
            Orders currentOrder = ordersOptional.get();
            orderRepository.delete(currentOrder);
            return new DefaultResponse(true, "", null);
        } else {
            return new DefaultResponse(false, "Order ID Not Found", null);
        }
    }

}
