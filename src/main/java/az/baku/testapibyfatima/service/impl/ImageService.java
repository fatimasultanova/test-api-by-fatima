package az.baku.testapibyfatima.service.impl;

import az.baku.testapibyfatima.dto.response.ExceptionResponse;
import az.baku.testapibyfatima.exception.ApplicationException;
import az.baku.testapibyfatima.exception.ExceptionEnums;
import az.baku.testapibyfatima.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Fatima Sultanova
 */

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductRepository productRepository;
    private String uploadDir = "C:\\Users\\Fatima\\Desktop\\test-api-by-fatima\\src\\main\\java\\az\\baku\\testapibyfatima\\uploads";

    public ResponseEntity<Resource> getImageAsJpg(long id) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(id + ".jpg").normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    public void saveFileToDatabase(MultipartFile file, long id) throws IOException {
        if (productRepository.existsById(id)) {
            String fileName = id + ".jpg";

            String filePath = uploadDir + File.separator + fileName;

            File destFile = new File(filePath);
            file.transferTo(destFile);
        } else {
            throw new ApplicationException(new ExceptionResponse(ExceptionEnums.PRODUCT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND));
        }
    }


    public ResponseEntity<String> findAndDeleteFileByName(long id) {
        File directory = new File(uploadDir);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new ApplicationException(new ExceptionResponse("Upload directory not found", HttpStatus.NOT_FOUND));
        }
        File[] files = directory.listFiles((dir, name) -> name.equals(id+".jpg"));
        if (files != null && files.length > 0) {
            File file1 = files[0];
            if (file1.delete()) {
                System.out.println("File deleted successfully: " + id+".jpg");
            } else {
                throw new ApplicationException(new ExceptionResponse("Failed to delete the file", HttpStatus.INTERNAL_SERVER_ERROR));
            }
        } else {
            throw new ApplicationException(new ExceptionResponse("File not found", HttpStatus.NOT_FOUND));
        }
        return null;
    }

    public void updateImage(MultipartFile file, long id) {
        findAndDeleteFileByName(id);
        try {
            saveFileToDatabase(file, id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
