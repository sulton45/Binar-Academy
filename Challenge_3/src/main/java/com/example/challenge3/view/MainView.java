package com.example.challenge3.view;

import com.example.challenge3.model.Menu;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class MainView {
    public void displayMenu() {
        System.out.println("===========================");
        System.out.println("Selamat datang di Binarfund");
        System.out.println("===========================");

        System.out.println("\nSilahkan pilih makanan : ");
        System.out.println("1. Nasi Goreng  | 15.000");
        System.out.println("2. Mie Goreng   | 13.000");
        System.out.println("3. Nasi + Ayam  | 18.000");
        System.out.println("4. Es Teh Manis | 3.000");
        System.out.println("5. Es Jeruk     | 5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");

        System.out.print("\n=> ");
    }

    public String getUserInput(String name, int price) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===========================");
        System.out.println("Berapa Pesanan Anda");
        System.out.println("===========================");

        System.out.printf("\n%s\t| %d", name, price);
        System.out.println("\n(input 0 untuk kembali)");

        System.out.print("\nqty => ");
        return scanner.nextLine();
    }

    public void displayOrderConfirmation(List<Menu> orderedItems) {
        // Display order confirmation and total
    }

    public void printOrder(PrintWriter writer, List<Menu> menuList) {
        int totalQuantity = 0;
        int totalHarga = 0;
        writer.println("================================");
        writer.println("Konfirmasi & Pembayaran");
        writer.println("================================\n");

        writer.println("Terima kasih sudah memesan");
        writer.println("di Binarfund");
        writer.println("\nDibawah ini adalah pesanan anda\n");
        for (Menu menu : menuList) {
            writer.println(menu.getName() + "\t" + menu.getQuantity() + "\t" + menu.getTotalPrice());
            totalQuantity += menu.getQuantity();
            totalHarga += menu.getTotalPrice();
        }
        writer.println("--------------------------------+");
        writer.printf("Total\t\t%d\t%d\n", totalQuantity, totalHarga);

        writer.println("\nPembayaran: BinarCash");
        writer.println("\n================================");
        writer.println("Simpan struk ini sebagai");
        writer.println("bukti pembayaran");
        writer.println("================================\n");
        writer.close();
    }


    public void displayErrorMessage() {
        System.out.println("\nMaaf anda memasukan nomor yang salah\n");
    }
}