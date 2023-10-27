package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.repositories.ProductRepository;
import com.example.ProjectBinarFood.views.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final MerchantService merchantService;
    private final Scanner scanner;

    @Autowired
    public ProductService(ProductRepository productRepository, MerchantService merchantService, ProductView productView) {
        this.productRepository = productRepository;
        this.merchantService = merchantService;
        this.scanner = new Scanner(System.in);
    }

    public List<Product> getAllProducts() {
        Iterable<Product> productsIterable = productRepository.findAll();
        List<Product> productsList = new ArrayList<>();
        productsIterable.forEach(productsList::add);
        return productsList;
    }

    public List<Product> getProductsByPriceGreaterThan(double price) {
        return productRepository.findAllByPriceGreaterThan(price);
    }

    public List<Product> getProductsByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            System.out.println("Product not found in the database for ID: " + id);
        }
        return product;
    }

    public void menuProduct(Merchant selectedMerchant) {
        boolean exit = false;

        while (!exit) {
            ProductView.displayMenu();
            int choice = 0;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan nomor pilihan yang benar.");
                scanner.nextLine();
            }

            List<Product> merchantProducts = getProductsByMerchant(selectedMerchant);

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Nama Produk (atau masukkan 0 untuk batal): ");
                    scanner.nextLine();
                    String productName = scanner.nextLine();
                    if ("0".equals(productName)) {
                        System.out.println("Pengguna membatalkan penambahan produk.");
                        break;
                    }
                    System.out.print("Masukkan Harga Produk (atau masukkan 0 untuk batal): ");
                    double productPrice;

                    if (scanner.hasNextDouble()) {
                        productPrice = scanner.nextDouble();
                        scanner.nextLine();
                        if (productPrice == 0) {
                            System.out.println("Pengguna membatalkan penambahan produk.");
                            break;
                        }
                    } else {
                        System.out.println("Input tidak valid. Harap masukkan harga yang benar.");
                        scanner.nextLine();
                        continue;
                    }
                    addProduct(productName, productPrice, selectedMerchant);
                    System.out.println("Produk telah ditambahkan.");
                    break;


                case 2:
                    System.out.print("Masukkan ID Produk yang akan diubah (atau masukkan 0 untuk batal): ");
                    String inputProductId = scanner.next();
                    if ("0".equals(inputProductId)) {
                        System.out.println("Pengguna membatalkan pengubahan produk.");
                        break;
                    }
                    UUID productId = UUID.fromString(inputProductId);
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Baru Produk (atau masukkan 0 untuk batal): ");
                    String newProductName = scanner.next();
                    if ("0".equals(newProductName)) {
                        System.out.println("Pengguna membatalkan pengubahan produk.");
                        break;
                    }
                    scanner.nextLine();
                    System.out.print("Masukkan Harga Baru Produk (atau masukkan 0 untuk batal): ");
                    String inputNewProductPrice = scanner.next();
                    if ("0".equals(inputNewProductPrice)) {
                        System.out.println("Pengguna membatalkan pengubahan produk.");
                        break;
                    }
                    double newProductPrice = Double.parseDouble(inputNewProductPrice);
                    scanner.nextLine();
                    updateProduct(productId, newProductName, newProductPrice, selectedMerchant);
                    System.out.println("Detail Produk telah diubah.");
                    break;

                case 3:
                    ProductView.displayAllProducts(merchantProducts);

                    System.out.print("Masukkan ID Produk yang akan dihapus (atau masukkan 0 untuk batal): ");
                    String inputDeleteProductId = scanner.next();
                    if ("0".equals(inputDeleteProductId)) {
                        System.out.println("Pengguna membatalkan penghapusan produk.");
                        break;
                    }
                    UUID deleteProductId = UUID.fromString(inputDeleteProductId);
                    scanner.nextLine();

                    Product productToDelete = getProductById(deleteProductId);
                    if (productToDelete != null && productToDelete.getMerchant().equals(selectedMerchant)) {
                        deleteProduct(deleteProductId);
                        System.out.println("Produk telah dihapus.");
                    } else {
                        System.out.println("Produk tidak ditemukan atau tidak milik merchant yang dipilih.");
                    }
                    break;

                case 4:
                    ProductView.displayAllProducts(merchantProducts);
                    break;

                case 5:
                    System.out.print("Masukkan Harga Minimum (atau masukkan 0 untuk batal): ");
                    double minPrice = scanner.nextDouble();
                    if (minPrice == 0) {
                        System.out.println("Pengguna membatalkan pencarian produk berdasarkan harga.");
                        break;
                    }
                    scanner.nextLine(); // Consume the newline character
                    List<Product> productsByPrice = getProductsByPriceGreaterThan(minPrice);
                    ProductView.displayProductsByPrice(productsByPrice);
                    break;

                case 6:
                    System.out.print("Masukkan Nama Produk (atau masukkan 0 untuk batal): ");
                    String searchName = scanner.next();
                    if ("0".equals(searchName)) {
                        System.out.println("Pengguna membatalkan pencarian produk berdasarkan nama.");
                        break;
                    }
                    scanner.nextLine(); // Consume the newline character
                    List<Product> productsByName = getProductsByName(searchName);
                    ProductView.displayProductsByName(productsByName);
                    break;

                case 7:
                    ProductView.showExitMessage();
                    exit = true;
                    break;
                default:
                    ProductView.showInvalidOptionMessage();
            }
        }
    }

    public void addProduct(String productName, double price, Merchant merchant) {
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setMerchant(merchant);
        saveProduct(product);
    }

    public void updateProduct(UUID id, String productName, double price, Merchant merchant) {
        Product product = getProductById(id);
        if (product != null) {
            product.setProductName(productName);
            product.setPrice(price);
            product.setMerchant(merchant);
            saveProduct(product);
        }
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public void showProductMenu() {
        Merchant selectedMerchant = merchantService.selectMerchant();

        if (selectedMerchant == null) {
            System.out.println("Merchant Tidak Ditemukan.");
            return;
        }

        System.out.println("Merchant: " + selectedMerchant.getMerchantName());
        menuProduct(selectedMerchant);
    }

    public List<Product> getProductsByMerchant(Merchant merchant) {
        return productRepository.findByMerchant(merchant);
    }
}