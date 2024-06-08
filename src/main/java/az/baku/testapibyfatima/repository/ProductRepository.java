package az.baku.testapibyfatima.repository;

import az.baku.testapibyfatima.entity.Category;
import az.baku.testapibyfatima.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Fatima Sultanova
 **/

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByPriceIsLessThanEqual(long price);
}