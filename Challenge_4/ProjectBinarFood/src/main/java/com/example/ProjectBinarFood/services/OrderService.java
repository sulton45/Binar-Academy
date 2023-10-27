package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.*;
import com.example.ProjectBinarFood.repositories.OrderRepository;
import com.example.ProjectBinarFood.views.OrderView;
import com.example.ProjectBinarFood.views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderView orderView;
    private final MerchantService merchantService;
    private final ProductService productService;
    private final Scanner scanner;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            OrderView orderView,
            MerchantService merchantService,
            ProductService productService
    ) {
        this.orderRepository = orderRepository;
        this.orderView = orderView;
        this.merchantService = merchantService;
        this.productService = productService;
        this.scanner = new Scanner(System.in); // Inisialisasi Scanner
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUser(Users user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getCompletedOrders() {
        return orderRepository.findByCompletedTrue();
    }

    public List<Order> getOrdersByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return orderRepository.findByOrderTimeBetween(startTime, endTime);
    }

    public List<Order> getOrdersByDestinationAddress(String destinationAddress) {
        return orderRepository.findByDestinationAdress(destinationAddress);
    }

    public void createOrder(Users user, String destinationAddress, List<OrderDetail> orderDetails) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setOrderTime(LocalDateTime.now());
        order.setDestinationAdress(destinationAddress);
        order.setUser(user);
        order.setCompleted(false);
        order.setOrderDetails(orderDetails);

        orderRepository.save(order);
    }

    public void showOrderMenu(Users user) {
        boolean exit = false;

        while (!exit) {
            orderView.displayMenu();
            int choice = orderView.getUserChoice();
            List<Order> userOrders = getOrdersByUser(user);

            switch (choice) {
                case 1:
                    createNewOrder(user);
                    break;
                case 2:
                    orderView.displayAllOrders(userOrders);
                    viewOrderDetails(userOrders);
                    break;
                case 3:
                    orderView.showExitMessage();
                    exit = true;
                    break;
                default:
                    orderView.showInvalidOptionMessage();
            }
        }
    }

    private void createNewOrder(Users user) {
        // Ambil informasi merchant dan produk
        Merchant selectedMerchant = merchantService.selectMerchant();
        if (selectedMerchant == null) {
            System.out.println("Merchant Tidak Ditemukan.");
            return;
        }

        // Menampilkan daftar produk dari merchant terpilih
        List<Product> merchantProducts = productService.getProductsByMerchant(selectedMerchant);
        ProductView.displayAllProducts(merchantProducts);

        try {
            System.out.print("Masukkan ID Produk yang akan dipesan (atau masukkan 0 untuk batal): ");
            String inputProductId = scanner.next();
            if ("0".equals(inputProductId)) {
                System.out.println("Pengguna membatalkan pembuatan pesanan.");
                return;
            }


            UUID productId = UUID.fromString(inputProductId);
            Product selectedProduct = productService.getProductById(productId);

            if (selectedProduct == null ) {
                if (selectedProduct == null) {
                    logger.error("Produk tidak ditemukan");
                    return;
                }
            }

            System.out.print("Masukkan jumlah produk yang akan dipesan: ");
            int quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Jumlah pesanan harus lebih dari 0.");
                return;
            }

            // Menyimpan pesanan
            List<OrderDetail> orderDetails = new ArrayList<>();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(selectedProduct);
            orderDetail.setQuantity(quantity);
            orderDetails.add(orderDetail);

            scanner.nextLine(); // Membersihkan newline
            System.out.print("Masukkan alamat tujuan pesanan: ");
            String destinationAddress = scanner.nextLine();

            this.createOrder(user, destinationAddress, orderDetails);
            logger.info("Pesanan telah dibuat.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat memproses pesanan. Pastikan input yang Anda masukkan benar.");

        }
    }

    private void viewOrderDetails(List<Order> userOrders) {
        if (userOrders.isEmpty()) {
            System.out.println("Pesanan Tidak Ditemukan.");
            return;
        }

        orderView.displayAllOrders(userOrders);

        try {
            System.out.print("Masukkan nomor pesanan untuk melihat detail (atau masukkan 0 untuk membatalkan): ");
            int orderNumber = scanner.nextInt();

            if (orderNumber == 0) {
                System.out.println("Batal lihat detail pesanan.");
                return;
            }

            int selectedIndex = orderNumber - 1;

            if (selectedIndex >= 0 && selectedIndex < userOrders.size()) {
                Order selectedOrder = userOrders.get(selectedIndex);
                orderView.displayOrderDetails(selectedOrder);
            } else {
                System.out.println("Nomor pesanan tidak valid.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid.");
            scanner.nextLine();
        }
    }
}