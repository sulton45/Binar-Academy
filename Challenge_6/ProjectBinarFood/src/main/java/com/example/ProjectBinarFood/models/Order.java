package com.example.ProjectBinarFood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private LocalDateTime orderTime;
        private String destinationAdress;

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "user_id",  nullable = false)
        private Users user;

        private Boolean completed;

        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
        @JsonIgnoreProperties("order")
        private List<OrderDetail> orderDetail;


        private UUID merchantId;

}