package az.baku.testapibyfatima.exception;

import lombok.Getter;

/**
 * @author Fatima Sultanova
 **/

@Getter
public enum ExceptionEnums {
   PRODUCT_NOT_FOUND("PRODUCT not found"),
   CATEGORY_NOT_FOUND("CATEGORY not found");

   private final String message;

   ExceptionEnums(String message) {
        this.message = message;
   }
}
