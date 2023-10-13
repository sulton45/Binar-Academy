package com.example.challenge3.services;

import com.example.challenge3.model.Menu;
import com.example.challenge3.view.MainView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BinarFoodService {

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

    public void orderMenu(String name, int price) {
        Scanner input = new Scanner(System.in);

        while (true) {
            view.displayMenu();
            String userInput = view.getUserInput(name, price);

            int qty = parseUserInput(userInput);

            if (qty == 0) {
                System.out.println("Pemesanan dibatalkan.");
                break;
            } else if (qty < 0) {
                System.out.println("Pesanan tidak boleh kurang dari 1");
            } else {
                int totalHarga = totalHarga(qty, price);
                Menu menuPesanan = new Menu(name, qty, price, totalHarga);
                menuList.add(menuPesanan);
                System.out.println("\nPesanan berhasil ditambahkan.\n");
                break;
            }
        }
    }

    public int totalHarga(int qty, int price){
        int total = qty * price;
        return total;
    }

    private int parseUserInput(String userInput) {
        return Optional.ofNullable(userInput)
                .map(Integer::parseInt)
                .orElse(0);
    }

    private boolean totalPesanan() {
        int totalQuantity = menuList.stream()
                .map(Menu::getQuantity)
                .mapToInt(Integer::intValue)
                .sum();

        int totalHarga = menuList.stream()
                .map(menu -> menu.getTotalPrice())
                .mapToInt(Integer::intValue)
                .sum();

        view.displayOrderConfirmation(menuList);

        menuList.forEach(menu -> {
            String name = menu.getName();
            String quantity = name.equals("Es Teh Manis") ? String.valueOf(menu.getQuantity()) : "\t" + menu.getQuantity();
            System.out.println(name + quantity + "\t" + menu.getTotalPrice());
        });

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
