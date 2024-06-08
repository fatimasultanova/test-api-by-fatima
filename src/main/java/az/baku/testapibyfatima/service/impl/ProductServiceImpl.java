package az.baku.testapibyfatima.service.impl;

import az.baku.testapibyfatima.dto.request.ProductRequest;
import az.baku.testapibyfatima.dto.response.ExceptionResponse;
import az.baku.testapibyfatima.dto.response.ProductResponse;
import az.baku.testapibyfatima.entity.Category;
import az.baku.testapibyfatima.entity.Product;
import az.baku.testapibyfatima.exception.ApplicationException;
import az.baku.testapibyfatima.exception.ExceptionEnums;
import az.baku.testapibyfatima.mapper.ProductMapper;
import az.baku.testapibyfatima.repository.CategoryRepository;
import az.baku.testapibyfatima.repository.ProductRepository;
import az.baku.testapibyfatima.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Fatima Sultanova
 **/

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Optional<Category> byName = categoryRepository.findByName(request.getCategory());
        if (byName.isPresent()) {
            product.setCategory(byName.get());
            productRepository.save(product);
            return mapper.toResponse(product);
        }
        throw new ApplicationException(new ExceptionResponse(ExceptionEnums.CATEGORY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND));
    }


    @Override
    public ProductResponse update(long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ApplicationException(new ExceptionResponse(ExceptionEnums.PRODUCT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
        Product product1 = mapper.partialUpdate(request, product);
        Optional<Category> byName = categoryRepository.findByName(request.getCategory());
        if (byName.isPresent()) {
            product1.setCategory(byName.get());
            Product updatedProduct = productRepository.save(product1);
            return mapper.toResponse(updatedProduct);
        }
        throw new ApplicationException(new ExceptionResponse(ExceptionEnums.CATEGORY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getById(long id) {
        Product byId = productRepository.findById(id).orElseThrow(()-> new ApplicationException(new ExceptionResponse(ExceptionEnums.PRODUCT_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
        return mapper.toResponse(byId);
    }

    @Override
    public Collection<ProductResponse> findAll() {
        List<Product> all = productRepository.findAll();
        return all.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> getAllByCategory(String category) {
        Optional<Category> byName = categoryRepository.findByName(category);
        return byName.map(productRepository::findAllByCategory).orElse(null);
    }

    @Override
    public List<Product> getAllByPriceIsLessThanEqual(long price) {
        return productRepository.findAllByPriceIsLessThanEqual(price);
    }

}
