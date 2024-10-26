package com.example.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInit {

    public FirebaseInit() {
        // Specify the path to your service account key JSON file
        String serviceAccountKeyFilePath = "src/main/resources/lead.json"; // Ensure this path is correct

        try {
            // Load the service account key JSON file
            FileInputStream serviceAccount = new FileInputStream(serviceAccountKeyFilePath);

            // Set up the Firebase options with the service account and database URL
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://dchronicles-1a08a-default-rtdb.firebaseio.com/") // Ensure this is the correct URL for your Realtime Database
                    .build();

            // Initialize Firebase only if not already initialized
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase Initialized Successfully");
            } else {
                System.out.println("Firebase is already initialized.");
            }

        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
