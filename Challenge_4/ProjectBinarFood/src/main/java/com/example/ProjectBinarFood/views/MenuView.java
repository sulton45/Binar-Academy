package com.example.ProjectBinarFood.views;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuView {
    public static void displayMainMenu() {
        System.out.println("Menu Utama:");
        System.out.println("1. Menu Merchant");
        System.out.println("2. Menu Produk");
        System.out.println("3. Menu User");
        System.out.println("4. Menu Order");
        System.out.println("5. Keluar");

        System.out.print("Pilih: ");
    }

    public static void showExitMessage() {
        System.out.println("Keluar.");
    }

    public static void showInvalidOptionMessage() {
        System.out.println("Opsi tidak valid. Silakan coba lagi.");
    }
}
