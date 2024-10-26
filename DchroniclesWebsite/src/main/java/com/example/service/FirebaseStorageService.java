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
    private final String bucketName = "dchronicles-1a08a.appspot.com";

    public FirebaseStorageService() throws IOException {
        try (FileInputStream serviceAccountStream = new FileInputStream("src/main/resources/lead.json")) {
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .setProjectId("dchronicles-1a08a") 
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new RuntimeException("Error initializing Firebase Storage: " + e.getMessage(), e);
        }
    }

    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String fullPath = folderName + "/" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fullPath)
                .setContentType(file.getContentType())
                .build();

        try {
            storage.create(blobInfo, file.getBytes());
        } catch (StorageException e) {
            System.err.println("Failed to upload file: " + e.getMessage());
            throw e; // Re-throw to handle it upstream
        }

        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", 
                              bucketName, 
                              URLEncoder.encode(fullPath, StandardCharsets.UTF_8.toString()));
    }
}
