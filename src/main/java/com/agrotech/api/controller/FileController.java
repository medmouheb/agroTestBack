package com.agrotech.api.controller;

import com.agrotech.api.Repository.FileRepository;
import com.agrotech.api.model.FileDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(file.getOriginalFilename());
            fileDocument.setContentType(file.getContentType());
            fileDocument.setData(file.getBytes());
            fileRepository.save(fileDocument);
            return ResponseEntity.status(HttpStatus.CREATED).body(fileDocument.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        Optional<FileDocument> fileDocumentOptional = fileRepository.findById(id);
        if (fileDocumentOptional.isPresent()) {
            FileDocument fileDocument = fileDocumentOptional.get();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileDocument.getFileName() + "\"")
                    .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileDocument.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public List<Map<String, String>>getAll() {
        List<FileDocument> entities = fileRepository.findAll();
        return entities.stream()
                .map(entity -> Map.of("id", entity.getId(), "fileName", entity.getFileName()))
                .collect(Collectors.toList());
    }
}
