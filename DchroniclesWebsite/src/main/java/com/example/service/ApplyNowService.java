package com.example.service;

import com.example.ApplyNow;
import com.example.repository.ApplyNowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplyNowService {

    @Autowired
    private ApplyNowRepository applyNowRepository;

    // Retrieve all ApplyNow posts
    public List<ApplyNow> getAllPosts() {
        return applyNowRepository.findAll();
    }

    // Save or update an application
    public void saveApplication(ApplyNow applyNow) {
        applyNowRepository.save(applyNow);  // Save the application to the database
    }

    // Delete an application by ID
    public boolean deleteApplication(Long id) {
        Optional<ApplyNow> applyNow = applyNowRepository.findById(id);
        if (applyNow.isPresent()) {
            applyNowRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Retrieve application by ID (optional, if needed)
    public Optional<ApplyNow> getApplicationById(Long id) {
        return applyNowRepository.findById(id);
    }
}
