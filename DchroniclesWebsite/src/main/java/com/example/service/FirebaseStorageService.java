package com.example.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    private final Storage storage;
    private final String bucketName = "dchronicles-1a08a.appspot.com"; // Your Firebase Storage bucket name
/*
    public FirebaseStorageService() throws IOException {
        // Load the service account key
        try (FileInputStream serviceAccountStream = new FileInputStream("src/main/resources/lead.json")) {
            // Initialize Firebase Storage with the service account credentials
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .setProjectId("dchronicles-1a08a") 
                    .build()
                    .getService();
        }
    }
*/
    public FirebaseStorageService() throws IOException {
        // Load the service account key from the classpath
        try (InputStream serviceAccountStream = getClass().getClassLoader().getResourceAsStream("lead.json")) {
            if (serviceAccountStream == null) {
                throw new FileNotFoundException("File lead.json not found in the classpath");
            }
            
            // Initialize Firebase Storage with the service account credentials
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .setProjectId("dchronicles-1a08a")
                    .build()
                    .getService();
        }
    }

    
    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Encode the file name to handle spaces and special characters
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());

        // Combine folder path and file name (e.g., "apply-now-resume/unique-filename.pdf" or "apply-now-cover-letter/unique-filename.pdf")
        String fullPath = folderName + "/" + encodedFileName;

        // Create a BlobInfo object to define the blob's metadata
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fullPath)
                .setContentType(file.getContentType())
                .build();

        // Upload the file to Firebase Storage
        storage.create(blobInfo, file.getBytes());

        // Construct the public URL for the uploaded file
        String publicUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", 
                                          bucketName, 
                                          URLEncoder.encode(fullPath, StandardCharsets.UTF_8.toString()));

        return publicUrl; // Return the public URL for the uploaded file
    }

}
