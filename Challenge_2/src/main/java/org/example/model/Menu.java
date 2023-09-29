package org.example.model;

public class Menu {
    private final int totalHarga;
    private String name;
    private int price;
    private int quantity;

    public Menu(String name, int quantity, int price, int totalHarga) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalHarga = totalHarga;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}