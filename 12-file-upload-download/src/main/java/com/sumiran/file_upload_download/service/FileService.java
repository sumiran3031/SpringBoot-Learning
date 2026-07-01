package com.sumiran.file_upload_download.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath,
                StandardCopyOption.REPLACE_EXISTING);

        return "File uploaded successfully: " + fileName;
    }

    public Resource downloadFile(String fileName)
            throws MalformedURLException {
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return resource;
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }

    public String[] listFiles() throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            return new String[]{};
        }
        return Files.list(uploadPath)
                .map(path -> path.getFileName().toString())
                .toArray(String[]::new);
    }

    public String deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Files.deleteIfExists(filePath);
        return "File deleted: " + fileName;
    }
}