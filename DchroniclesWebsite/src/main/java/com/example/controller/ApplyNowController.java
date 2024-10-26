package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.ApplyNow;
import com.example.repository.ApplyNowRepository;
import com.example.service.ApplyNowService;
import com.example.service.FirebaseStorageService;

import java.io.IOException;
import java.util.List;

@Controller
public class ApplyNowController {

    @Autowired
    private ApplyNowService applyNowService;

    @Autowired
    private ApplyNowRepository applyNowRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    // Display all applications
    @GetMapping("/apply")
    public String getAllApplications(Model model) {
        List<ApplyNow> applications = applyNowRepository.findAll();
        model.addAttribute("applications", applications);
        return "Applynow"; // HTML view for displaying all applications
    }

    // API to return all applications in JSON format
    @GetMapping("/applynow")
    @ResponseBody
    public List<ApplyNow> getAllApplicationsJson() {
        return applyNowRepository.findAll();
    }

    // Get a specific application by ID
    @GetMapping("/apply/{id}")
    @ResponseBody
    public ApplyNow getApplicationById(@PathVariable Long id) {
        return applyNowRepository.findById(id)
                .orElse(null); // Return null if not found (handle this differently if needed)
    }

    @PostMapping("/apply")
    public String addApplication(@RequestParam String fullName,
                                 @RequestParam String email,
                                 @RequestParam String phoneNumber,
                                 @RequestParam String positionAppliedFor,
                                 @RequestParam int yearsOfExperience,
                                 @RequestParam String highestLevelOfEducation,
                                 @RequestParam String courseName,
                                 @RequestParam String coverLetter, // Now handled as String
                                 @RequestParam MultipartFile resume,  // For resume upload
                                 Model model) {
        ApplyNow applyNow = new ApplyNow();
        applyNow.setFullName(fullName);
        applyNow.setEmail(email);
        applyNow.setPhoneNumber(phoneNumber);
        applyNow.setPositionAppliedFor(positionAppliedFor);
        applyNow.setYearsOfExperience(yearsOfExperience);
        applyNow.setHighestLevelOfEducation(highestLevelOfEducation);
        applyNow.setCourseName(courseName);
        
        // Set the plain text cover letter
        applyNow.setCoverLetter(coverLetter);

        // Upload resume file
        if (!resume.isEmpty()) {
            try {
                String resumeUrl = firebaseStorageService.uploadFile(resume, "apply now resume");
                applyNow.setResume(resumeUrl); // Store the resume URL
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Failed to upload resume. Please try again.");
                return "career"; // Return to the form with an error message
            }
        }

        // Save the application to the database
        applyNowService.saveApplication(applyNow);
        return "redirect:/success"; // Redirect to a success page after submission
    }

    
    // Update an existing application by ID
    @PutMapping("/apply/{id}")
    @ResponseBody
    public String updateApplication(@PathVariable Long id, @RequestBody ApplyNow updatedApplication) {
        return applyNowRepository.findById(id)
                .map(existingApplication -> {
                    existingApplication.setFullName(updatedApplication.getFullName());
                    existingApplication.setEmail(updatedApplication.getEmail());
                    existingApplication.setPhoneNumber(updatedApplication.getPhoneNumber());
                    existingApplication.setPositionAppliedFor(updatedApplication.getPositionAppliedFor());
                    existingApplication.setYearsOfExperience(updatedApplication.getYearsOfExperience());
                    existingApplication.setHighestLevelOfEducation(updatedApplication.getHighestLevelOfEducation());
                    existingApplication.setCourseName(updatedApplication.getCourseName());
                    existingApplication.setCoverLetter(updatedApplication.getCoverLetter());
                    existingApplication.setResume(updatedApplication.getResume());
                    applyNowRepository.save(existingApplication);
                    return "Application updated successfully!";
                })
                .orElse("Application not found");
    }

    // Delete an application by ID
    @DeleteMapping("/apply/{id}")
    @ResponseBody
    public String deleteApplication(@PathVariable Long id) {
        return applyNowService.deleteApplication(id) ?
                "Application deleted successfully!" :
                "Application not found"; // Return appropriate message if application does not exist
    }
}
