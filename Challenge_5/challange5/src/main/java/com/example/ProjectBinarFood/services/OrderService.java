package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.*;
import com.example.ProjectBinarFood.payloads.requests.CreateOrderRequest;
import com.example.ProjectBinarFood.payloads.requests.OrderDTO;
import com.example.ProjectBinarFood.payloads.requests.OrderDetailDTO;
import com.example.ProjectBinarFood.repositories.MerchantRepository;
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
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private OrderDetailsService orderDetailsService;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(UUID uuid){

        Optional<Order> order = orderRepository.findById(uuid);
        if(!order.isPresent()){
            try {
                throw new Exception("Order not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return order.get();
    }

    public List<Order> getOrdersByUser(Users user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getCompletedOrders() {
        return orderRepository.findByCompletedTrue();
    }

    public List<Order> getOrdersByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return orderRepository.findByOrderTimeBetween(startTime, endTime);
    }

    public List<Order> getOrdersByDestinationAddress(String destinationAddress) {
        return orderRepository.findByDestinationAdress(destinationAddress);
    }

    public ResponseEntity<String> createOrder(OrderDTO request) {

        Order order = new Order();
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        Optional<Users> user = usersRepository.findById(UUID.fromString(request.getUserId()));

        UUID id = UUID.randomUUID();
        order.setId(id);
        order.setUser(user.get());
        order.setMerchantId(UUID.fromString(request.getMerchantId()));
        order.setOrderTime(LocalDateTime.now());
        order.setDestinationAdress(request.getDestinationAddress());
        order.setCompleted(false);
        orderRepository.save(order);

        return new ResponseEntity<>("Order created successfully", HttpStatus.OK);

//        try {
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
//        }
    }
}