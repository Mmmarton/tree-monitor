package training.nuttyyokel.dto;

import org.springframework.http.HttpStatus;

/**
 * @author DrMarcell
 */
public class FieldErrorResponse {

  private String defaultMessage;
  private String field;

  public FieldErrorResponse() {
  }

  public String getMessage() {
    return defaultMessage;
  }

  public void setDefaultMessage(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }
}
