package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Users;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ProjectBinarFood.services.UsersService;
import com.example.ProjectBinarFood.payloads.requests.UsersDTO;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("binarfood/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody UsersDTO usersDTO) {
        try {
            Users user = usersService.addUser(usersDTO.getUsername(), usersDTO.getEmailAddress(), usersDTO.getPassword());
            if (user != null) {
                return ResponseEntity.ok("User Berhasil Dibuat");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") String id,
            @RequestBody UsersDTO usersDTO
    ) {
        UUID userId = UUID.fromString(id);
        try {
            Users user = usersService.updateUser(userId, usersDTO.getUsername(), usersDTO.getEmailAddress(), usersDTO.getPassword());
            if (user != null) {
                return ResponseEntity.ok("User Behasil di Update");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        UUID userId = UUID.fromString(id);
        try {
            usersService.deleteUser(userId);
            return ResponseEntity.ok("User Berhasil Dihapus");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Tidak Ditemukan");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("findall")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<Users> users = usersService.getAllUsers();

        // Map Users entities to UsersDTO
        List<UsersDTO> userDTOs = users.stream()
                .map(user -> {
                    UsersDTO usersDTO = new UsersDTO();
                    usersDTO.setId(user.getId());
                    usersDTO.setUsername(user.getUsername());
                    usersDTO.setEmailAddress(user.getEmailAddress());
                    usersDTO.setPassword(user.getPassword());
                    return usersDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable("id") String id) {
        UUID userId = UUID.fromString(id);
        Users user = usersService.getUserById(userId);

        if (user != null) {
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(user.getId());
            usersDTO.setUsername(user.getUsername());
            usersDTO.setEmailAddress(user.getEmailAddress());
            return ResponseEntity.ok(usersDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}