package com.example.ProjectBinarFood.controllers;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.payloads.requests.MerchantDTO;
import com.example.ProjectBinarFood.services.MerchantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("binarfood/merchant/")
@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("findall")
    public List<Merchant> getAllMerchant(){
        return merchantService.getAllMerchants();
    }

    @GetMapping("findopen")
    public List<Merchant> getOpenMerchant(){
        return merchantService.getOpenMerchants();
    }

    @PostMapping("create")
    public ResponseEntity<String> saveMerchant(@RequestBody MerchantDTO merchantDTO){
        merchantService.addMerchant(merchantDTO.getMerchantName(), merchantDTO.getMerchantLocation());
        return ResponseEntity.ok("Merchant berhasil dibuat.");
    }

    @GetMapping("findby/{id}")
    public Merchant getMerchantById(@PathVariable("id") String id){
        return merchantService.selectMerchant(id);
    }


    @DeleteMapping("delete/{id}")
    public void deleteMerchantById(@PathVariable("id") String id) {
        UUID merchantId = UUID.fromString(id);
        merchantService.deleteMerchant(merchantId);
    }

    @PutMapping("toggle/{id}")
    public ResponseEntity<String> toggleMerchantStatus(@PathVariable("id") String id) {
        UUID merchantId = UUID.fromString(id);
        try {
            boolean isOpen = merchantService.toggleMerchantStatus(merchantId);
            String statusMessage = isOpen ? "Merchant Buka" : "Merchant Tutup";
            return ResponseEntity.ok(statusMessage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Merchant Tidak Ditemukan");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }

    @GetMapping("findactive")
    public List<Merchant> getActiveMerchants() {
        return merchantService.getActiveMerchants();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateMerchant(@PathVariable("id") String id, @RequestBody MerchantDTO merchantDTO) {
        UUID merchantId = UUID.fromString(id);
        try {
            merchantService.updateMerchant(merchantId, merchantDTO);
            return ResponseEntity.ok("Informasi Merchant Berhasil Diubah");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Merchant Tidak Ditemukan");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR.");
        }
    }

}