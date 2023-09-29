package org.example.controller;

import org.example.model.Menu;
import org.example.view.MainView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarFoodController {
    private final List<Menu> menuList = new ArrayList<>();
    private final MainView view = new MainView();

    public void start() {
        boolean loop = true;
        Scanner input = new Scanner(System.in);

        while (loop) {
            view.displayMenu();
            String choice = input.next();

            switch (choice) {
                case "1":
                    orderMenu("Nasi Goreng", 15000);
                    break;
                case "2":
                    orderMenu("Mie Goreng", 13000);
                    break;
                case "3":
                    orderMenu("Nasi + Ayam", 18000);
                    break;
                case "4":
                    orderMenu("Es Teh Manis", 3000);
                    break;
                case "5":
                    orderMenu("Es Jeruk", 5000);
                    break;
                case "99":
                    loop = totalPesanan();
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    view.displayErrorMessage();
            }
        }
    }

    private void orderMenu(String name, int price) {
        Scanner input = new Scanner(System.in);

        while (true) {
            view.displayMenu();
            String userInput = view.getUserInput(name, price);

            try {
                int qty = Integer.parseInt(userInput);

                if (qty == 0) {
                    System.out.println("Pemesanan dibatalkan.");
                    break;
                } else if (qty < 0) {
                    System.out.println("Pesanan tidak boleh kurang dari 1");
                } else {
                    int totalHarga = qty * price;
                    Menu menuPesanan = new Menu(name, qty, price, totalHarga);
                    menuList.add(menuPesanan);
                    System.out.println("\nPesanan berhasil ditambahkan.\n");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nMasukkan data yang benar.\n");
            }
        }
    }

    private boolean totalPesanan() {
        int totalQuantity = 0;
        int totalHarga = 0;

        view.displayOrderConfirmation(menuList);

        for (Menu menu : menuList) {
            if (menu.getName().equals("Es Teh Manis")) {
                System.out.println(menu.getName() + "\t" + menu.getQuantity() + "\t" + menu.getTotalPrice());
            } else {
                System.out.println(menu.getName() + "\t\t" + menu.getQuantity() + "\t" + menu.getTotalPrice());
            }
            totalQuantity += menu.getQuantity();
            totalHarga += menu.getTotalPrice();
        }

        System.out.println("--------------------------------+");
        System.out.printf("Total\t\t%d\t%d\n", totalQuantity, totalHarga);

        Scanner input = new Scanner(System.in);
        System.out.println("\n1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("\n=> ");
        String choice = input.next();

        switch (choice) {
            case "1":
                try {
                    PrintWriter writer = new PrintWriter("pesanan.txt", "UTF-8");
                    printPesanan(writer, menuList);
                    menuList.clear();
                    return true;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

            case "2":
                return true;

            case "0":
                return false;

            default:
                return true;
        }
    }

    private void printPesanan(PrintWriter writer, List<Menu> menuList) {
        view.printOrder(writer, menuList);
    }
}