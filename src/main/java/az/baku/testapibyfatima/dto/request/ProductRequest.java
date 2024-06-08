package az.baku.testapibyfatima.dto.request;

import lombok.*;

/**
 * @author Fatima Sultanova
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductRequest {
    String name;
    long price;
    long count;
    String category;
}
