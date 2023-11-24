package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.payloads.requests.ProductDTO;
import com.example.ProjectBinarFood.services.MerchantService;
import com.example.ProjectBinarFood.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequestMapping("binarfood/product/")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("findall")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        UUID productId = UUID.fromString(id);
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Merchant merchant = merchantService.retrieveMerchant(productDTO.getMerchantId());

            productService.addProduct(productDTO.getProductName(), productDTO.getPrice(), merchant);
            return ResponseEntity.ok("Produk Berhasil Dibuat");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable("id") String id,
            @RequestBody ProductDTO productDTO
    ) {
        UUID productId = UUID.fromString(id);
        try {
            Merchant merchant = merchantService.retrieveMerchant(productDTO.getMerchantId());

            productService.updateProduct(productId, productDTO.getProductName(), productDTO.getPrice(), merchant);
            return ResponseEntity.ok("Produk Berhasil di Update");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        UUID productId = UUID.fromString(id);
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Produk Berhasil Dihapus");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }

    @GetMapping("findbymerchant/{merchantId}")
    public List<Product> getProductsByMerchant(@PathVariable("merchantId") String merchantId) {
        UUID merchantUUID = UUID.fromString(merchantId);
        Merchant merchant = merchantService.getMerchantById(merchantUUID);

        if (merchant != null) {
            return productService.getProductsByMerchant(merchant);
        } else {
            return Collections.emptyList();
        }
    }
}