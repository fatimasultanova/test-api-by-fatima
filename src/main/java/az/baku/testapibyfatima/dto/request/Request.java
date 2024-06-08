package az.baku.testapibyfatima.dto.request;

import lombok.*;

/**
 * @author Fatima Sultanova
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Request<R> {
    private long id;
    private R request;
}
