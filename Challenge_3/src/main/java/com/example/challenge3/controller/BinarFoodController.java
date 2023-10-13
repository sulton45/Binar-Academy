package com.example.challenge3.controller;

import com.example.challenge3.services.BinarFoodService;
import org.springframework.beans.factory.annotation.Autowired;

public class BinarFoodController {

    @Autowired
    private BinarFoodService binarFoodService;

    public void start(){
        binarFoodService.start();
    }

}