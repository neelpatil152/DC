
package com.example.controller;

import java.util.List;
import java.util.Optional; // Add this import for Optional

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Enquiry;
import com.example.repository.EnquiryRepository;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @GetMapping("/enquiry")
    public String showEnquiryForm(Model model) {
        model.addAttribute("enquiry", new Enquiry());
        return "Enquiryform";  // The view name for the form
    }

   /*
    @PostMapping("/enquiry")
    public ResponseEntity<String> submitEnquiryForm(@RequestBody Enquiry enquiry) {
        // Log the received enquiry for debugging
        System.out.println("Received enquiry: " + enquiry);
        enquiryRepository.save(enquiry);
        return ResponseEntity.status(HttpStatus.CREATED).body("Enquiry submitted successfully!");
    }
*/
    
    @PostMapping("/enquiry")
    public String submitEnquiryForm(Enquiry enquiry) {
        // Log the received enquiry for debugging
        System.out.println("Received enquiry: " + enquiry);
        enquiryRepository.save(enquiry);
        return "redirect:/success"; // Redirect to success page after saving
    }

    // Retrieve all enquiries
    @GetMapping("/enquiries")
    public ResponseEntity<List<Enquiry>> getAllEnquiries() {
        List<Enquiry> enquiries = enquiryRepository.findAll();
        return ResponseEntity.ok(enquiries);
    }

    // Retrieve a specific enquiry by ID
    @GetMapping("/enquiry/{id}")
    public ResponseEntity<?> getEnquiryById(@PathVariable Long id) {
        Optional<Enquiry> enquiry = enquiryRepository.findById(id);
        if (enquiry.isPresent()) {
            return ResponseEntity.ok(enquiry.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Enquiry with ID " + id + " does not exist.");
        }
    }

    // Handle PUT requests to update an existing enquiry
    @PutMapping("/enquiry/{id}")
    public ResponseEntity<String> updateEnquiry(@PathVariable Long id, @ModelAttribute Enquiry enquiry) {
        if (!enquiryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Enquiry with ID " + id + " does not exist.");
        }
        enquiry.setId(id); // Assuming your Enquiry class has a setId method
        enquiryRepository.save(enquiry);
        return ResponseEntity.ok("Enquiry updated successfully!");
    }

    // Handle DELETE requests to delete an enquiry
    @DeleteMapping("/enquiry/{id}")
    public ResponseEntity<String> deleteEnquiry(@PathVariable Long id) {
        if (!enquiryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Enquiry with ID " + id + " does not exist.");
        }
        enquiryRepository.deleteById(id);
        return ResponseEntity.ok("Enquiry deleted successfully!");
    }
}
