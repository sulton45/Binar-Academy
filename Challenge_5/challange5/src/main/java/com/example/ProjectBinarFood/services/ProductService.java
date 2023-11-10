package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerchantService merchantService;

    public List<Product> getAllProducts() {
        Iterable<Product> productsIterable = productRepository.findAll();
        List<Product> productsList = new ArrayList<>();
        productsIterable.forEach(productsList::add);
        return productsList;
    }


    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product findById(UUID id){

        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            try {
                throw new Exception("Product not found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return product.get();
    }

    public Product getProductById(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            System.out.println("Produk tidak ditemukan dengan id berikut: " + id);
        }
        return product;
    }

    public void addProduct(String productName, Double price, Merchant merchant) {
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

    public void showProductMenu(String merchantId) {
        Merchant selectedMerchant = merchantService.selectMerchant(merchantId);

        if (selectedMerchant == null) {
            System.out.println("Merchant Tidak Ditemukan.");
            return;
        }

        System.out.println("Merchant: " + selectedMerchant.getMerchantName());
    }

    public List<Product> getProductsByMerchant(Merchant merchant) {
        return productRepository.findByMerchant(merchant);
    }
}