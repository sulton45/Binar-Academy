package com.example.challenge3.service;

import com.example.challenge3.services.BinarFoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinarFoodServiceTest {

    private BinarFoodService service = new BinarFoodService();

    @Test
    @DisplayName("Positive Case - Pesan Nasi Goreng 5")
    void hitungTotalHargaNasiGorengLima(){
        var result = service.totalHarga(5, 15000);
        Assertions.assertEquals(75000, result);
    }

    @Test
    @DisplayName("Positive Case - Pesan Mie Goreng 3")
    void hitungTotalHargaMieGorengTiga(){
        var result = service.totalHarga(3, 13000);
        Assertions.assertEquals(39000, result);
    }


    @Test
    @DisplayName("Positive Case - Pesan Es Teh Manis 8")
    void hitungTotalHargaEsTehDelapan(){
        var result = service.totalHarga(8, 3000);
        Assertions.assertEquals(24000, result);
    }

    @Test
    @DisplayName("Positive Case - Pesan Es Jeruk 9")
    void hitungTotalHargaEsJerukSembilan(){
        var result = service.totalHarga(9, 5000);
        Assertions.assertEquals(45000, result);
    }

    @Test
    @DisplayName("Negative Case - Harga Nol")
    void hitungTotalHargaHargaNol() {
        var result = service.totalHarga(5, 0);
        Assertions.assertEquals(0, result);
    }



}
