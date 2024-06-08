package az.baku.testapibyfatima.service.impl;

import az.baku.testapibyfatima.dto.request.CategoryRequest;
import az.baku.testapibyfatima.dto.response.CategoryResponse;
import az.baku.testapibyfatima.dto.response.ExceptionResponse;
import az.baku.testapibyfatima.entity.Category;
import az.baku.testapibyfatima.exception.ApplicationException;
import az.baku.testapibyfatima.exception.ExceptionEnums;
import az.baku.testapibyfatima.mapper.CategoryMapper;
import az.baku.testapibyfatima.repository.CategoryRepository;
import az.baku.testapibyfatima.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fatima Sultanova
 **/

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        categoryRepository.save(category);
        return mapper.toResponse(category);
    }

    @Override
    public CategoryResponse update(long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ApplicationException(new ExceptionResponse(ExceptionEnums.CATEGORY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
        Category updatedCategory = mapper.partialUpdate(request, category);
        categoryRepository.save(updatedCategory);
        return mapper.toResponse(updatedCategory);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse getById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ApplicationException(new ExceptionResponse(ExceptionEnums.CATEGORY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
        return mapper.toResponse(category);
    }
    @Override
    public CategoryResponse getByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(() ->
                new ApplicationException(new ExceptionResponse(ExceptionEnums.CATEGORY_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND)));
        return mapper.toResponse(category);
    }


    @Override
    public Collection<CategoryResponse> findAll() {
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(mapper::toResponse).collect(Collectors.toList());
    }
}
