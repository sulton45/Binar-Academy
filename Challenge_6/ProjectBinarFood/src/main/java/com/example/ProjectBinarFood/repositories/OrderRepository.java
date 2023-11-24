package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser(Users user);

    List<Order> findByOrderTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<Order> findByCompletedTrue();

    List<Order> findByDestinationAdress(String destinationAdress);

    Optional<Order> findById(UUID id);
}
