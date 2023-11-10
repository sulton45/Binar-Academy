package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.payloads.requests.MerchantDTO;
import com.example.ProjectBinarFood.repositories.MerchantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public List<Merchant> getOpenMerchants() {
        return merchantRepository.findByOpen(true);
    }

    public void saveMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public Merchant getMerchantById(UUID id) {
        return merchantRepository.findById(id).orElse(null);
    }


    public void addMerchant(String merchantName, String merchantLocation) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantName);
        merchant.setMerchantLocation(merchantLocation);
        merchant.setOpen(true);
        saveMerchant(merchant);
    }

    public boolean toggleMerchantStatus(UUID merchantId) {
        Merchant merchant = getMerchantById(merchantId);
        if (merchant != null) {
            merchant.setOpen(!merchant.isOpen());
            saveMerchant(merchant);
            return merchant.isOpen();
        } else {
            throw new EntityNotFoundException("Merchant with ID " + merchantId + " not found.");
        }
    }

    public Merchant selectMerchant(String id) {
        UUID merchantId = UUID.fromString(id);

        return getMerchantById(merchantId);
    }

    public void deleteMerchant(UUID merchantId) {
        if (merchantRepository.existsById(merchantId)) {
            merchantRepository.deleteById(merchantId);
        } else {
            throw new EntityNotFoundException("ID Merchant " + merchantId + " tidak ditemukan");
        }
    }

    public List<Merchant> getActiveMerchants() {
        return merchantRepository.findByOpen(true);
    }

    public void updateMerchant(UUID merchantId, MerchantDTO merchantDTO) {
        Merchant merchant = getMerchantById(merchantId);
        if (merchant != null) {
            merchant.setMerchantName(merchantDTO.getMerchantName());
            merchant.setMerchantLocation(merchantDTO.getMerchantLocation());
            saveMerchant(merchant);
        } else {
            throw new EntityNotFoundException("Merchant dengan ID " + merchantId + " Tidak Ditemukan");
        }
    }

    public Merchant retrieveMerchant(UUID merchantId) {
        Merchant existingMerchant = getMerchantById(merchantId);
        if (existingMerchant != null) {
            return existingMerchant;
        } else {
            throw new EntityNotFoundException("Merchant with ID " + merchantId + " not found.");
        }
    }


}