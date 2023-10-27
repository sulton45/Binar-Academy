package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Users;
import com.example.ProjectBinarFood.repositories.UsersRepository;
import com.example.ProjectBinarFood.views.UsersView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersView usersView;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersView usersView) {
        this.usersRepository = usersRepository;
        this.usersView = usersView;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
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
        usersRepository.deleteById(id);
    }

    public void menuUsers() {
        boolean exit = false;

        while (!exit) {
            usersView.displayMenu();
            int choice = usersView.getUserChoice();
            List<Users> allUsers = getAllUsers();
            switch (choice) {
                case 1:
                    System.out.print("Masukkan Nama Pengguna (atau masukkan 0 untuk batal): ");
                    String username = usersView.getStringInput();
                    if ("0".equals(username)) {
                        System.out.println("Pengguna membatalkan penambahan pengguna.");
                        break;
                    }
                    System.out.print("Masukkan Alamat Email (atau masukkan 0 untuk batal): ");
                    String emailAddress = usersView.getStringInput();
                    if ("0".equals(emailAddress)) {
                        System.out.println("Pengguna membatalkan penambahan pengguna.");
                        break;
                    }
                    System.out.print("Masukkan Password (atau masukkan 0 untuk batal): ");
                    String password = usersView.getStringInput();
                    if ("0".equals(password)) {
                        System.out.println("Pengguna membatalkan penambahan pengguna.");
                        break;
                    }
                    addUser(username, emailAddress, password);
                    System.out.println("Pengguna telah ditambahkan.");
                    break;

                case 2:
                    usersView.displayAllUsers(allUsers);
                    System.out.print("Masukkan ID Pengguna yang akan diubah (atau masukkan 0 untuk batal): ");
                    String inputUserId = usersView.getStringInput();
                    if ("0".equals(inputUserId)) {
                        System.out.println("Pengguna membatalkan perubahan pengguna.");
                        break;
                    }
                    UUID userId = usersView.getUUIDFromString(inputUserId);
                    if (userId != null) {
                        System.out.print("Masukkan Nama Pengguna yang baru: ");
                        String updatedUsername = usersView.getStringInput();
                        System.out.print("Masukkan Alamat Email yang baru: ");
                        String updatedEmailAddress = usersView.getStringInput();
                        System.out.print("Masukkan Password yang baru: ");
                        String updatedPassword = usersView.getStringInput();
                        updateUser(userId, updatedUsername, updatedEmailAddress, updatedPassword);
                        System.out.println("Data Pengguna telah diubah.");
                    } else {
                        System.out.println("ID Pengguna tidak valid.");
                    }
                    break;

                case 3:
                    usersView.displayAllUsers(allUsers);
                    break;

                case 4:
                    usersView.displayAllUsers(allUsers);
                    System.out.print("Masukkan ID Pengguna yang akan dihapus (atau masukkan 0 untuk batal): ");
                    String inputDeleteUserId = usersView.getStringInput();
                    if ("0".equals(inputDeleteUserId)) {
                        System.out.println("Pengguna membatalkan penghapusan pengguna.");
                        break;
                    }
                    UUID deleteUserId = usersView.getUUIDFromString(inputDeleteUserId);
                    if (deleteUserId != null) {
                        deleteUser(deleteUserId);
                        System.out.println("Pengguna telah dihapus.");
                    } else {
                        System.out.println("ID Pengguna tidak valid.");
                    }
                    break;

                case 5:
                    usersView.showExitMessage();
                    exit = true;
                    break;

                default:
                    usersView.showInvalidOptionMessage();
            }
        }
    }
}