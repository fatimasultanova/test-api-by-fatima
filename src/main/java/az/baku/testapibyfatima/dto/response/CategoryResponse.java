package az.baku.testapibyfatima.dto.response;

import lombok.*;

import java.util.List;

/**
 * @author Fatima Sultanova
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryResponse {
    private long id;
    private String name;
    private List<Long> productList;
}
