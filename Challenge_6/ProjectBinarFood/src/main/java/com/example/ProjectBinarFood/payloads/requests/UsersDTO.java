package com.example.ProjectBinarFood.payloads.requests;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UsersDTO {
    private UUID id;
    private String username;
    private String emailAddress;
    private String password;
    private String otp;
}