package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findByOpen(boolean open);
}
