package az.baku.testapibyfatima.service;

import az.baku.testapibyfatima.dto.request.CategoryRequest;
import az.baku.testapibyfatima.dto.response.CategoryResponse;

/**
 * @author Fatima Sultanova
 **/

public interface CategoryService extends CrudService<CategoryRequest, CategoryResponse> {
    CategoryResponse getByName(String name);
}
