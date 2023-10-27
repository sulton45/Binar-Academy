package com.example.ProjectBinarFood.repositories;

import com.example.ProjectBinarFood.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);
    Users findByEmailAddress(String emailAddress);
    Users findByUsernameAndPassword(String username, String password);
}
