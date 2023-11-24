package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.*;
import com.example.ProjectBinarFood.payloads.requests.OrderDTO;
import com.example.ProjectBinarFood.repositories.MerchantRepository;
import com.example.ProjectBinarFood.repositories.OrderDetailRepository;
import com.example.ProjectBinarFood.repositories.OrderRepository;
import com.example.ProjectBinarFood.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    private ProductService productService;
    @Autowired
    private UsersService usersService;


    public List<Order> getAllOrdersWithDetails() {
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
            order.setOrderDetail(orderDetails);
        }

        return orders;
    }

    public ResponseEntity<String> createOrder(OrderDTO request) {
        Users user = usersService.getUserById(UUID.fromString(request.getUserId()));
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Product product = productService.getProductById(request.getProductId());
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        Order order = createNewOrder(request, user);
        if (order == null) {
            return new ResponseEntity<>("Failed to create order", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        OrderDetail orderDetail = createNewOrderDetail(order, product, request);
        if (orderDetail == null) {
            return new ResponseEntity<>("Failed to create order detail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Order created successfully", HttpStatus.OK);
    }

    private Order createNewOrder(OrderDTO request, Users user) {
        Order order = new Order();
        order.setUser(user);
        order.setMerchantId(UUID.fromString(request.getMerchantId()));
        order.setOrderTime(LocalDateTime.now());
        order.setDestinationAdress(request.getDestinationAddress());
        order.setCompleted(false);
        return orderRepository.save(order);
    }

    private OrderDetail createNewOrderDetail(Order order, Product product, OrderDTO request) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setTotalPrice(product.getPrice() * request.getQuantity());
        return orderDetailRepository.save(orderDetail);
    }

    public Order getOrderById(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }
}