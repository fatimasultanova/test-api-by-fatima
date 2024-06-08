package az.baku.testapibyfatima.controller;

import az.baku.testapibyfatima.dto.request.CategoryRequest;
import az.baku.testapibyfatima.dto.request.Request;
import az.baku.testapibyfatima.dto.response.CategoryResponse;
import az.baku.testapibyfatima.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Fatima Sultanova
 **/

@RestController
@CrossOrigin(origins = "*", maxAge = 10800)
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        CategoryResponse categoryResponse = categoryService.create(request);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> update(@RequestBody Request<CategoryRequest> request) {
        CategoryResponse categoryResponse = categoryService.update(request.getId(), request.getRequest());
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Request<CategoryRequest> request) {
        categoryService.delete(request.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<CategoryResponse> getById(@RequestBody Request<CategoryRequest> request) {
        CategoryResponse categoryResponse = categoryService.getById(request.getId());
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponse> getByName(@PathVariable String name) {
        CategoryResponse categoryResponse = categoryService.getByName(name);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CategoryResponse>> findAll() {
        Collection<CategoryResponse> categoryResponses = categoryService.findAll();
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }
}
