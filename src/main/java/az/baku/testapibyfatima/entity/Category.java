package az.baku.testapibyfatima.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Fatima Sultanova
 **/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(schema = "test-api", name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public static Category fromString(String name) {
        if (name != null) {
            Category category = new Category();
            category.setName(name);
            return category;
        }
        return null;
    }
}
