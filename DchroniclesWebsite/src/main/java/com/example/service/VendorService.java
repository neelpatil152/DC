package com.example.service;

import com.example.Vendor;
import com.example.repository.VendorRepository; // Ensure you have a repository interface for Vendor
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public void saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll(); // Fetch all vendors
    }

    public Vendor getVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        return vendor.orElse(null); // Return vendor if found, otherwise null
    }

    public boolean deleteVendor(Long id) {
        if (vendorRepository.existsById(id)) {
            vendorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
