package com.example.ProjectBinarFood;

import com.example.ProjectBinarFood.controllers.MenuController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjectBinarFoodApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProjectBinarFoodApplication.class, args);
        MenuController menuController = context.getBean(MenuController.class);

        menuController.showMainMenu();
    }
}