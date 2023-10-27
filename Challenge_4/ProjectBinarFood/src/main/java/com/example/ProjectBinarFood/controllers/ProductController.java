package com.example.ProjectBinarFood.controllers;


import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.services.MerchantService;
import com.example.ProjectBinarFood.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantService merchantService;

    public void showProductMenu() {
        Merchant selectedMerchant = merchantService.selectMerchant();

        if (selectedMerchant == null) {
            System.out.println("Merchant Tidak Ditembukan.");
            return;
        }

        System.out.println("Merchant: " + selectedMerchant.getMerchantName());
        productService.menuProduct(selectedMerchant);
    }
}