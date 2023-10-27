package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.OrderDetail;
import com.example.ProjectBinarFood.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByProduct(Product product);
}
