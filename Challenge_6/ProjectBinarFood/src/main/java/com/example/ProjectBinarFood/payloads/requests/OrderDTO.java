package com.example.ProjectBinarFood.payloads.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDTO {

    private String userId;
    private String destinationAddress;
    private String merchantId;
    private UUID order;
    private UUID productId;
    private Integer quantity;
    private double totalPrice;

}
