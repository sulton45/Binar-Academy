package com.example.ProjectBinarFood.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "merchant")
@Entity

public class Merchant {


        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private String merchantName;
        private String merchantLocation;
        private boolean open;
}
