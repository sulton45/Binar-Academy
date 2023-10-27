package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    public void showUsersMenu() {
        usersService.menuUsers();
    }
}