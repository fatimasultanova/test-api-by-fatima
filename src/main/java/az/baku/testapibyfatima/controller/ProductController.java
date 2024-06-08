package az.baku.testapibyfatima.controller;

import az.baku.testapibyfatima.dto.request.*;
import az.baku.testapibyfatima.dto.response.*;
import az.baku.testapibyfatima.entity.Product;
import az.baku.testapibyfatima.exception.ApplicationException;
import az.baku.testapibyfatima.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

/**
 * @author Fatima Sultanova
 **/

@RestController
@CrossOrigin(origins = "*", maxAge = 10800)
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.create(request);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductResponse> update(@RequestBody Request<ProductRequest> request) {
        ProductResponse productResponse = productService.update(request.getId(), request.getRequest());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Request<ProductRequest> request) {
        productService.delete(request.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<ProductResponse> getProductById(@RequestBody Request<ProductRequest> request) {
        try {
            ProductResponse productResponse = productService.getById(request.getId());
            return ResponseEntity.ok(productResponse);
        } catch (ApplicationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<ProductResponse>> findAll() {
        Collection<ProductResponse> productResponses = productService.findAll();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
        List<Product> products = productService.getAllByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getAllByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Product>> getProductsByPrice(@PathVariable long price) {
        List<Product> products = productService.getAllByPriceIsLessThanEqual(price);
        return ResponseEntity.ok(products);
    }
}
