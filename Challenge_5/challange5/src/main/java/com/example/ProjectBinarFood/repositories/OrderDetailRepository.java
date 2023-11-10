package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.OrderDetail;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.models.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findAllById(Users users);

    List<OrderDetail> findAll();
}
