package com.example.challenge3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.challenge3.controller.BinarFoodController;

@SpringBootApplication
public class Challange3Application {

	public static void main(String[] args) {
		SpringApplication.run(Challange3Application.class, args);
		BinarFoodController controller = new BinarFoodController();
		controller.start();
	}

}
