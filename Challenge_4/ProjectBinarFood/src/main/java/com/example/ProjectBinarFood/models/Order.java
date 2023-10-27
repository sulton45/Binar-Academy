package com.example.ProjectBinarFood.models;

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

        @ManyToOne
        @JoinColumn(name = "user_id",  nullable = false)
        private Users user;

        private Boolean completed;

        @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
        private List<OrderDetail> orderDetail;

        public void setOrderDetails(List<OrderDetail> orderDetails) {
                this.orderDetail = orderDetails;
        }
}