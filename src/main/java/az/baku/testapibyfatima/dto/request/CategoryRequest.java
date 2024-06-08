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
public class CategoryRequest {
    private String name;
}
