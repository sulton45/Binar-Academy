package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.Users;
import com.example.ProjectBinarFood.models.OrderDetail;
import com.example.ProjectBinarFood.payloads.requests.CreateOrderRequest;
import com.example.ProjectBinarFood.payloads.requests.OrderDTO;
import com.example.ProjectBinarFood.payloads.requests.OrderDetailDTO;
import com.example.ProjectBinarFood.services.OrderDetailsService;
import com.example.ProjectBinarFood.services.OrderService;
import com.example.ProjectBinarFood.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("binarfood/orders/")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("findall2")
    public List<Order> findAll(){
        return orderService.findAll();
    }
//    @PostMapping("create")
//    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
//        try {
//            Users user = usersService.getUserById(UUID.fromString(request.getUserId()));
//
//            if (user == null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
//            }
//
//            List<OrderDetail> orderDetails = request.getOrderDetails();
//            String destinationAddress = request.getDestinationAddress();
//
//            if (orderDetails == null || orderDetails.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order details are required.");
//            }
//
//            orderService.createOrder(user, destinationAddress, orderDetails);
//            return ResponseEntity.ok("Order created successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the order.");
//        }
//    }

    @GetMapping("findall")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}