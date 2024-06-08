package az.baku.testapibyfatima.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author Fatima Sultanova
 **/

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExceptionResponse {
    String message;
    HttpStatus httpStatus;

    public ExceptionResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
