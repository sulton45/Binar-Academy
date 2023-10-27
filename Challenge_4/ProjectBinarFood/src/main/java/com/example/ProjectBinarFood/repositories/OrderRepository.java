package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(Users user);

    List<Order> findByOrderTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<Order> findByCompletedTrue();

    List<Order> findByDestinationAdress(String destinationAdress);
}
