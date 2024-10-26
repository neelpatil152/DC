package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ApplyNow;
import com.example.ContactUs;
import com.example.Enquiry;

import com.example.Vendor;
import com.example.repository.ApplyNowRepository;
import com.example.repository.ContactUsRepository;
import com.example.repository.EnquiryRepository;

import com.example.repository.VendorRepository;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Autowired
    private ApplyNowRepository joinOurTeamRepository;

    @Autowired
    private EnquiryRepository ourTeamRepository;

    @Autowired
    private VendorRepository teamRepository;

    // Admin login page
    @GetMapping("/admin")
    public String showAdminLoginPage() {
        return "adminlogin"; // The admin login page
    }

    // Handle admin login
    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "adminlogin"; // Return to the login page with an error
        }
    }
/*
    // Admin dashboard to display both Contact Us and Join Our Team data
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<ContactUs> contactUsList = contactUsRepository.findAll(); // Fetch all ContactUs entries
        List<ApplyNow> joinOurTeamList = joinOurTeamRepository.findAll();
        List<Enquiry> TeamList = OurTeamRepository.findAll();
        List<Vendor> OurTeamList = TeamRepository.findAll();// Fetch all JoinOurTeam entries

        model.addAttribute("contactUsList", contactUsList); // Add Contact Us data to the model
        model.addAttribute("ApplyNowList", joinOurTeamList);
        model.addAttribute("EnquiryList", joinOurTeamList);
        model.addAttribute("VendorList", joinOurTeamList);
        return "dashboard"; // Admin dashboard page
    }
*/
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<ContactUs> contactUsList = contactUsRepository.findAll(); // Fetch all ContactUs entries
        List<ApplyNow> joinOurTeamList = joinOurTeamRepository.findAll(); // Assuming this is for the Apply Now form
        List<Enquiry> enquiryList = ourTeamRepository.findAll(); // Fetch all Enquiry entries
        List<Vendor> vendorList = teamRepository.findAll(); // Fetch all Vendor entries

        model.addAttribute("contactUsList", contactUsList); // Add Contact Us data to the model
        model.addAttribute("ApplyNowList", joinOurTeamList); // Correctly add Apply Now data
        model.addAttribute("EnquiryList", enquiryList); // Add Enquiry data to the model
        model.addAttribute("VendorList", vendorList); // Add Vendor data to the model

        return "dashboard"; // Admin dashboard page
    }

    
    // Handle delete Contact Us entry
    @PostMapping("/admin/delete/contact/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactUsRepository.deleteById(id);
        return "redirect:/admin/dashboard"; // Redirect back to the dashboard
    }

    // Handle delete Join Our Team entry
    @PostMapping("/admin/delete/join/{id}")
    public String deleteJoinOurTeam(@PathVariable Long id) {
        joinOurTeamRepository.deleteById(id);
        return "redirect:/admin/dashboard"; // Redirect back to the dashboard
    }

    // Show update form for a contact us entry
  @GetMapping("/admin/update/{id}")
  public String showUpdateContactForm(@PathVariable Long id, Model model) {
      ContactUs contactUs = contactUsRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
      model.addAttribute("contactUs", contactUs); // Add the contact us object to the model
      return "update_contact"; // Return the update form page
  }

  @PostMapping("/admin/update")
  public String updateContact(@ModelAttribute ContactUs contactUs, RedirectAttributes redirectAttributes) {
      contactUsRepository.save(contactUs); // Save the updated contact us entry
      redirectAttributes.addFlashAttribute("successMessage", "Contact updated successfully!"); // Add success message
      return "redirect:/admin/dashboard"; // Redirect back to the dashboard
 }
}

