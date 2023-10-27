package com.example.ProjectBinarFood.views;

import com.example.ProjectBinarFood.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductView {
    public static void displayMenu() {
        System.out.println("Menu Produk:");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Ubah Detail Produk");
        System.out.println("3. Hapus Produk");
        System.out.println("4. Tampilkan Semua Produk");
        System.out.println("5. Tampilkan Produk Berdasarkan Harga");
        System.out.println("6. Tampilkan Produk Berdasarkan Nama");
        System.out.println("7. Kembali ke Menu Utama");
        System.out.print("Pilih: ");
    }

    public static void displayAllProducts(List<Product> products) {
        System.out.println("Daftar Semua Produk:");
        for (Product product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Nama: " + product.getProductName());
            System.out.println("Harga: " + product.getPrice());
            System.out.println();
        }
    }

    public static void displayProductsByPrice(List<Product> products) {
        System.out.println("Daftar Produk Berdasarkan Harga:");
        for (Product product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Nama: " + product.getProductName());
            System.out.println("Harga: " + product.getPrice());
            System.out.println();
        }
    }

    public static void displayProductsByName(List<Product> products) {
        System.out.println("Daftar Produk Berdasarkan Nama:");
        for (Product product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Nama: " + product.getProductName());
            System.out.println("Harga: " + product.getPrice());
            System.out.println();
        }
    }

    public static void showExitMessage() {
        System.out.println();
        System.out.println("Keluar.");
        System.out.println();
    }

    public static void showInvalidOptionMessage() {
        System.out.println();
        System.out.println("Opsi tidak valid. Silakan coba lagi.");
        System.out.println();
    }
}