package com.example.ProjectBinarFood.payloads.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {

    private String productName;
    private Double price;
    private UUID merchantId;

}
