package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {

    List<Product> findByMerchant(Merchant merchant);
}