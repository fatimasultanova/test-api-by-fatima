package az.baku.testapibyfatima.exception;

import az.baku.testapibyfatima.dto.response.ExceptionResponse;
import lombok.Getter;

/**
 * @author Fatima Sultanova
 **/

@Getter
public class ApplicationException extends RuntimeException {

    private final ExceptionResponse exceptionResponse;

    public ApplicationException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse.getMessage());
        this.exceptionResponse = exceptionResponse;
    }

}
