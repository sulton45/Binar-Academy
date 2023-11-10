package com.example.ProjectBinarFood.payloads.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class UsersDTO {
    private UUID id;
    private String username;
    private String emailAddress;
    private String password;

    public UsersDTO() {
    }

    public UsersDTO(UUID id, String username, String emailAddress, String password) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}