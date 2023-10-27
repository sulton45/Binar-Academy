package com.example.ProjectBinarFood.views;

import com.example.ProjectBinarFood.models.Merchant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantView {
    public static void displayMenu() {
        System.out.println("Menu Merchant:");
        System.out.println("1. Tambah Merchant");
        System.out.println("2. Ubah Status Merchant");
        System.out.println("3. Tampilkan Semua Merchant");
        System.out.println("4. Tampilkan Merchant yang Sedang Buka");
        System.out.println("5. Kembali ke Menu Utama");
        System.out.print("Pilih : ");
    }

    public static void displayAllMerchants(List<Merchant> merchants) {
        System.out.println("Daftar Semua Merchant:");
        for (Merchant merchant : merchants) {
            System.out.println("ID: " + merchant.getId());
            System.out.println("Nama: " + merchant.getMerchantName());
            System.out.println("Lokasi: " + merchant.getMerchantLocation());
            System.out.println("Status: " + (merchant.isOpen() ? "Buka" : "Tutup"));
            System.out.println("---------------------------------------");
        }
    }

    public static void displayOpenMerchants(List<Merchant> openMerchants) {
        System.out.println("Daftar Merchant Buka:");
        for (Merchant merchant : openMerchants) {
            System.out.println("ID: " + merchant.getId());
            System.out.println("Nama: " + merchant.getMerchantName());
            System.out.println("Lokasi: " + merchant.getMerchantLocation());
            System.out.println("---------------------------------------");
        }
    }

    public static void showExitMessage() {
        System.out.println();
        System.out.println("Keluar dari menu Merchant");
        System.out.println();
    }

    public static void showInvalidOptionMessage() {
        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
    }
}