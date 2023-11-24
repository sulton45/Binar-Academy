package com.example.ProjectBinarFood.payloads.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDetailDTO {

    private UUID order;
    private UUID product;
    private Integer quantity;
    private double totalPrice;

}
