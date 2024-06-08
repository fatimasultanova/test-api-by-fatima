package az.baku.testapibyfatima.mapper;

import az.baku.testapibyfatima.dto.request.ProductRequest;
import az.baku.testapibyfatima.dto.response.ProductResponse;
import az.baku.testapibyfatima.entity.Category;
import az.baku.testapibyfatima.entity.Product;
import org.mapstruct.*;

/**
 * @author Fatima Sultanova
 **/

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

    default String mapCategory(Category category) {
        return category.getName();
    }

    default Category mapC(String name) {
       return Category.fromString(name);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Product partialUpdate(ProductRequest productRequest, @MappingTarget Product product);
}