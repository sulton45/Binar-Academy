package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Users;
import com.example.ProjectBinarFood.views.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.ProjectBinarFood.services.UsersService;

import java.util.Scanner;
import java.util.UUID;

@Controller
public class MenuController {
    @Autowired
    private MenuView menuView;
    @Autowired
    private UsersService usersService;
    @Autowired
    private MerchantController merchantController;
    @Autowired
    private ProductController productController;
    @Autowired
    private UsersController usersController;
    @Autowired
    private OrderController orderController;


    public void showMainMenu() {
        boolean exit = false;

        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            menuView.displayMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    merchantController.showMerchantMenu();
                    break;
                case 2:
                    productController.showProductMenu();
                    break;
                case 3:
                    usersController.showUsersMenu();
                    break;
                case 4:
                    System.out.print("Masukkan ID Pengguna: ");
                    UUID userId = UUID.fromString(scanner.next());
                    Users user = usersService.getUserById(userId);
                    orderController.showOrderMenu(user);
                    break;
                case 5:
                    System.out.println("Keluar dari aplikasi.");
                    exit = true;
                    break;
                default:
                    MenuView.showInvalidOptionMessage();
            }
        }
        scanner.close();
    }
}