package com.example.ProjectBinarFood.views;

import com.example.ProjectBinarFood.models.Order;
import com.example.ProjectBinarFood.models.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class OrderView {
    private final Scanner scanner;

    public OrderView() {
        this.scanner = new Scanner(System.in);
    }

    public int getUserChoice() {
        System.out.print("Pilih : ");
        return scanner.nextInt();
    }

    public String getStringInput() {
        return scanner.nextLine().trim();
    }

    public void displayMenu() {
        System.out.println("==== Menu Pemesanan ====");
        System.out.println("1. Buat Pesanan Baru");
        System.out.println("2. Lihat Pesanan Saya");
        System.out.println("3. Keluar");
    }

    public void showExitMessage() {
        System.out.println("Terima kasih. Sampai jumpa!");
    }

    public void showInvalidOptionMessage() {
        System.out.println("Pilihan tidak valid. Silakan pilih opsi yang benar.");
    }

    public void displayAllOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("Tidak ada pesanan yang tersedia.");
            return;
        }

        System.out.println("==== Daftar Pesanan ====");
        for (Order order : orders) {
            System.out.println("ID Pesanan: " + order.getId());
            System.out.println("Waktu Pesan: " + order.getOrderTime());
            System.out.println("Alamat Tujuan: " + order.getDestinationAdress());

            List<OrderDetail> orderDetails = order.getOrderDetail();
            System.out.println("Item dalam Pesanan: ");
            for (OrderDetail orderDetail : orderDetails) {
                System.out.println("Nama Produk: " + orderDetail.getProduct().getProductName());
                System.out.println("Jumlah: " + orderDetail.getQuantity());
                System.out.println("Harga: " + orderDetail.getTotalPrice());
            }
            System.out.println();
        }
    }

    public void displayOrderDetails(Order order) {
        System.out.println("==== Detail Pesanan ====");
        System.out.println("ID Pesanan: " + order.getId());
        System.out.println("Waktu Pesan: " + order.getOrderTime());
        System.out.println("Alamat Tujuan: " + order.getDestinationAdress());
        System.out.println("User: " + order.getUser().getUsername());
        System.out.println("Completed: " + (order.getCompleted() ? "Yes" : "No"));

        List<OrderDetail> orderDetails = order.getOrderDetail();
        System.out.println("Item dalam Pesanan: ");
        for (OrderDetail orderDetail : orderDetails) {
            System.out.println("Nama Produk: " + orderDetail.getProduct().getProductName());
            System.out.println("Jumlah: " + orderDetail.getQuantity());
            System.out.println("Harga per Unit: " + orderDetail.getProduct().getPrice());
            System.out.println("Total Harga: " + orderDetail.getTotalPrice());
        }
    }


}