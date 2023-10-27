package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Users;
import com.example.ProjectBinarFood.services.OrderService;
import com.example.ProjectBinarFood.views.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderView orderView;

    @Autowired
    public OrderController(OrderService orderService, OrderView orderView) {
        this.orderService = orderService;
        this.orderView = orderView;
    }

    public void showOrderMenu(Users user) {
        orderService.showOrderMenu(user);
    }
}