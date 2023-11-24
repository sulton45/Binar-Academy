package com.example.ProjectBinarFood.payloads.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class MerchantDTO {

    private UUID id;
    private String merchantName;
    private String merchantLocation;

}
