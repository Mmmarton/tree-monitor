package training.nuttyyokel.dto;

import org.springframework.http.HttpStatus;

/**
 * @author DrMarcell
 */
public class FieldErrorDTO {

  private String defaultMessage;
  private String field;

  public FieldErrorDTO() {
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
