package com.sumiran.file_upload_download.controller;

import com.sumiran.file_upload_download.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {
        try {
            String message = fileService.uploadFile(file);
            return ResponseEntity.ok(message);
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName) {
        try {
            Resource resource = fileService.downloadFile(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\""
                                    + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<String[]> listFiles() {
        try {
            return ResponseEntity.ok(fileService.listFiles());
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(
            @PathVariable String fileName) {
        try {
            return ResponseEntity.ok(fileService.deleteFile(fileName));
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Delete failed: " + e.getMessage());
        }
    }
}