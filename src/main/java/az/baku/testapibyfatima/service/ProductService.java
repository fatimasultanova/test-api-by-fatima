package az.baku.testapibyfatima.service;

import az.baku.testapibyfatima.dto.request.ProductRequest;
import az.baku.testapibyfatima.dto.response.ProductResponse;
import az.baku.testapibyfatima.entity.Product;

import java.util.List;

/**
 * @author Fatima Sultanova
 **/

public interface ProductService extends CrudService<ProductRequest, ProductResponse> {
    List<Product> getAllByName(String name);
    List<Product> getAllByCategory(String category);
    List<Product> getAllByPriceIsLessThanEqual(long price);
}
