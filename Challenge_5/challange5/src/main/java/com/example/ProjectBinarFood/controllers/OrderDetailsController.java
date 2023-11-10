package com.example.ProjectBinarFood.controllers;


import com.example.ProjectBinarFood.payloads.requests.OrderDetailDTO;
import com.example.ProjectBinarFood.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("binarfood/orderdetail/")
@RestController
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("create")
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO){

        orderDetailsService.createOrderDetail(orderDetailDTO);
        return new ResponseEntity<>("Order detail created successfully", HttpStatus.OK);
    }

}
