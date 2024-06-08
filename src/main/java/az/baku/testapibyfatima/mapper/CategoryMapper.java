package az.baku.testapibyfatima.mapper;

import az.baku.testapibyfatima.dto.request.CategoryRequest;
import az.baku.testapibyfatima.dto.response.CategoryResponse;
import az.baku.testapibyfatima.entity.Category;
import az.baku.testapibyfatima.entity.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fatima Sultanova
 **/

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);

    default List<Long> products(List<Product> products) {
        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Category partialUpdate(CategoryRequest categoryRequest, @MappingTarget Category category);
}