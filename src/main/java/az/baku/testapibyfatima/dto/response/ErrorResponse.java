package az.baku.testapibyfatima.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Fatima Sultanova
 **/

@NoArgsConstructor
@ToString
@Setter
@Getter
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String type;
    private LocalDateTime localTime;
    private String errorMessage;

    public ErrorResponse(HttpStatus httpStatus, LocalDateTime localTime, String errorMessage) {
        this.httpStatus = httpStatus;
        this.localTime = localTime;
        this.errorMessage = errorMessage;
        this.type="ERROR";
}
}
