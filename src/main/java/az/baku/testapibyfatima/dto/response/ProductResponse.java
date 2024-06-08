package az.baku.testapibyfatima.dto.response;

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
public class ProductResponse {
    private long id;
    private String name;
    private long price;
    private long count;
    private String category;
}
