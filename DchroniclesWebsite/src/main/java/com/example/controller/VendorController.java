package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.Vendor;
import com.example.repository.VendorRepository;
import com.example.service.VendorService;
import com.example.service.FirebaseStorageService;

import java.io.IOException;
import java.util.List;

@Controller

public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    // Display all vendors
    @GetMapping("/vendor")
    public String getAllVendors(Model model) {
        List<Vendor> vendors = vendorRepository.findAll();
        model.addAttribute("vendors", vendors);
        return "Vendor"; // HTML view for displaying all vendors
    }

    // API to return all vendors in JSON format
    @GetMapping("/vendors")
    @ResponseBody
    public List<Vendor> getAllVendorsJson() {
        return vendorRepository.findAll();
    }

    // Get a specific vendor by ID
    @GetMapping("/vendor/{id}")
    @ResponseBody
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorRepository.findById(id)
                .orElse(null); // Return null if not found
    }

    // Add a new vendor
    @PostMapping("/vendor")
    public String addVendor(@RequestParam String companyName,
                            @RequestParam String companyWebsite,
                            @RequestParam String phoneNumber,
                            @RequestParam String country,
                            @RequestParam String address,
                            @RequestParam String district,
                            @RequestParam String postalCode,
                            @RequestParam String companyId,
                            @RequestParam MultipartFile uploadDocument, // For document upload
                            Model model) {
        Vendor vendor = new Vendor();
        vendor.setCompanyName(companyName);
        vendor.setCompanyWebsite(companyWebsite);
        vendor.setPhoneNumber(phoneNumber);
        vendor.setCountry(country);
        vendor.setAddress(address);
        vendor.setDistrict(district);
        vendor.setPostalCode(postalCode);
        vendor.setCompanyId(companyId);

        // Upload document file
        if (!uploadDocument.isEmpty()) {
            try {
                String documentUrl = firebaseStorageService.uploadFile(uploadDocument, "vendor documents");
                vendor.setUploadDocument(documentUrl); // Store the document URL
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Failed to upload document. Please try again.");
                return "vendorForm"; // Return to the form with an error message
            }
        }

        // Save the vendor to the database
        vendorService.saveVendor(vendor);
        return "redirect:/success"; // Redirect to a success page after submission
    }

    // Update an existing vendor by ID
    @PutMapping("/vendor/{id}")
    @ResponseBody
    public String updateVendor(@PathVariable Long id, @RequestBody Vendor updatedVendor) {
        return vendorRepository.findById(id)
                .map(existingVendor -> {
                    existingVendor.setCompanyName(updatedVendor.getCompanyName());
                    existingVendor.setCompanyWebsite(updatedVendor.getCompanyWebsite());
                    existingVendor.setPhoneNumber(updatedVendor.getPhoneNumber());
                    existingVendor.setCountry(updatedVendor.getCountry());
                    existingVendor.setAddress(updatedVendor.getAddress());
                    existingVendor.setDistrict(updatedVendor.getDistrict());
                    existingVendor.setPostalCode(updatedVendor.getPostalCode());
                    existingVendor.setCompanyId(updatedVendor.getCompanyId());
                    existingVendor.setUploadDocument(updatedVendor.getUploadDocument());
                    vendorRepository.save(existingVendor);
                    return "Vendor updated successfully!";
                })
                .orElse("Vendor not found");
    }

    // Delete a vendor by ID
    @DeleteMapping("/vendor/{id}")
    @ResponseBody
    public String deleteVendor(@PathVariable Long id) {
        return vendorService.deleteVendor(id) ?
                "Vendor deleted successfully!" :
                "Vendor not found"; // Return appropriate message if vendor does not exist
    }
}
