package com.example.ProjectBinarFood.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Table (name = "users")
@Entity
public class Users {

    @Id
    private UUID id;
    private String username;
    private String emailAddress;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
