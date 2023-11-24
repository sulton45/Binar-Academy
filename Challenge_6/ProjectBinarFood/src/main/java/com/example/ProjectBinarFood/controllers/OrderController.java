package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.payloads.requests.OrderDTO;
import com.example.ProjectBinarFood.services.OrderService;
import com.example.ProjectBinarFood.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("binarfood/orders/")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UsersService usersService;

    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("findall")
    public List<Order> getAllOrdersWithDetails() {
        return orderService.getAllOrdersWithDetails();
    }

    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }
}