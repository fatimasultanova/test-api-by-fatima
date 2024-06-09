package az.baku.testapibyfatima.controller;

import az.baku.testapibyfatima.dto.request.ProductRequest;
import az.baku.testapibyfatima.dto.request.Request;
import az.baku.testapibyfatima.exception.ApplicationException;
import az.baku.testapibyfatima.service.impl.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Fatima Sultanova
 */

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*", maxAge = 10800)
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<Resource> getImage(@RequestBody Request<ProductRequest> request){
        return imageService.getImageAsJpg(request.getId());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) {
        try {
            imageService.saveFileToDatabase(file, id);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteImage(Request<ProductRequest> request) {
        return imageService.findAndDeleteFileByName(request.getId());
    }

    @PutMapping
    public ResponseEntity<String> updateImage(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) {
        try {
            imageService.updateImage(file, id);
            return ResponseEntity.ok("Image updated successfully");
        }catch (ApplicationException e){
            return ResponseEntity.status(500).body("File update failed: " + e.getMessage());
        }
    }
}

