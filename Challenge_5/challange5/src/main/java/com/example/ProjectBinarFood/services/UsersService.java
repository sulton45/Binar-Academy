package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Users;
import com.example.ProjectBinarFood.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users findById(UUID id){

        Optional<Users> users = usersRepository.findById(id);
        if(!users.isPresent()){
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return users.get();

    }

    public Users getUserById(UUID id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users addUser(String username, String emailAddress, String password) {
        Users user = new Users();
        user.setId(UUID.randomUUID());
        user.setUsername(username);
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
        return usersRepository.save(user);
    }

    public Users updateUser(UUID id, String username, String emailAddress, String password) {
        Users existingUser = usersRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(username);
            existingUser.setEmailAddress(emailAddress);
            existingUser.setPassword(password);
            return usersRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(UUID id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User dengan ID " + id + " Tidak Ditemukan");
        }
    }

}