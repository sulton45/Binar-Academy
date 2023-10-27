package com.example.ProjectBinarFood.controllers;


import com.example.ProjectBinarFood.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    public void showMerchantMenu() {
        merchantService.menuMerchant();
    }
}