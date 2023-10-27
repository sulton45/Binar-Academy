package com.example.ProjectBinarFood.views;

import com.example.ProjectBinarFood.models.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class UsersView {
    private final Scanner scanner;

    public UsersView() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Menu Pengguna:");
        System.out.println("1. Tambah Pengguna");
        System.out.println("2. Ubah Pengguna");
        System.out.println("3. Tampilkan Semua Pengguna");
        System.out.println("4. Hapus Pengguna");
        System.out.println("5. Kembali ke Menu Utama");
        System.out.print("Pilih : ");
    }

    public void displayAllUsers(List<Users> users) {
        System.out.println("Daftar Semua Pengguna:");
        for (Users user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email Address: " + user.getEmailAddress());
            System.out.println("---------------------------------------");
        }
    }

    public void showExitMessage() {
        System.out.println();
        System.out.println("Keluar dari menu Pengguna");
        System.out.println();
    }

    public void showInvalidOptionMessage() {
        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
    }

    public String getStringInput() {
        return scanner.nextLine().trim();
    }

    public UUID getUUIDFromString(String input) {
        try {
            return UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
        }
        return choice;
    }
}