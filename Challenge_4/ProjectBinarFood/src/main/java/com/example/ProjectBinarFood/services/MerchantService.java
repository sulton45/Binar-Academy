package com.example.ProjectBinarFood.services;

import com.example.ProjectBinarFood.models.Merchant;
import com.example.ProjectBinarFood.models.Product;
import com.example.ProjectBinarFood.repositories.MerchantRepository;
import com.example.ProjectBinarFood.views.MerchantView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Service
public class MerchantService {
    private final MerchantRepository merchantRepository;
    private final Scanner scanner;

    @Autowired
    public MerchantService(MerchantRepository merchantRepository, MerchantView merchantView) {
        this.merchantRepository = merchantRepository;
        this.scanner = new Scanner(System.in);
    }

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

    public void menuMerchant() {
        boolean exit = false;

        while (!exit) {
            MerchantView.displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Nama Merchant (atau masukkan 0 untuk batal): ");
                    String merchantName = scanner.nextLine().trim();
                    if ("0".equals(merchantName)) {
                        System.out.println("Pengguna membatalkan penambahan merchant.");
                        break;
                    }
                    System.out.print("Masukkan Lokasi Merchant (atau masukkan 0 untuk batal): ");
                    String merchantLocation = scanner.nextLine().trim();
                    if ("0".equals(merchantLocation)) {
                        System.out.println("Pengguna membatalkan penambahan merchant.");
                        break;
                    }
                    addMerchant(merchantName, merchantLocation);
                    System.out.println("Merchant telah ditambahkan.");
                    break;

                case 2:
                    System.out.print("Masukkan ID Merchant yang akan diubah statusnya (atau masukkan 0 untuk batal): ");
                    String inputMerchantId = scanner.nextLine().trim();
                    if ("0".equals(inputMerchantId)) {
                        System.out.println("Pengguna membatalkan perubahan status merchant.");
                        break;
                    }
                    UUID merchantId = getUUIDFromString(inputMerchantId);
                    if (merchantId != null) {
                        toggleMerchantStatus(merchantId);
                        System.out.println("Status Merchant telah diubah.");
                    } else {
                        System.out.println("ID Merchant tidak valid.");
                    }
                    break;

                case 3:
                    List<Merchant> allMerchants = getAllMerchants();
                    MerchantView.displayAllMerchants(allMerchants);
                    break;

                case 4:
                    List<Merchant> openMerchants = getOpenMerchants();
                    MerchantView.displayOpenMerchants(openMerchants);
                    break;

                case 5:
                    MerchantView.showExitMessage();
                    exit = true;
                    break;
                default:
                    MerchantView.showInvalidOptionMessage();
            }
        }
    }

    private int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            // Handle non-integer input
        }
        return choice;
    }

    private UUID getUUIDFromString(String input) {
        try {
            return UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            return null; // Invalid UUID
        }
    }

    public void addMerchant(String merchantName, String merchantLocation) {
        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantName);
        merchant.setMerchantLocation(merchantLocation);
        merchant.setOpen(true);
        saveMerchant(merchant);
    }

    public void toggleMerchantStatus(UUID merchantId) {
        Merchant merchant = getMerchantById(merchantId);
        if (merchant != null) {
            merchant.setOpen(!merchant.isOpen());
            saveMerchant(merchant);
        }
    }
    public Merchant selectMerchant() {
        List<Merchant> allMerchants = getAllMerchants();
        MerchantView.displayAllMerchants(allMerchants);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukan ID Merchant yang Ingin Dipilih: ");
        String inputMerchantId = scanner.next();
        UUID merchantId = UUID.fromString(inputMerchantId);

        return getMerchantById(merchantId);
    }
}