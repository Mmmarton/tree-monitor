package training.nuttyyokel.dto;

import org.springframework.http.HttpStatus;

public class FieldErrorResponse extends GenericResponse {

  private String field;

  public FieldErrorResponse() {
    setStatus(HttpStatus.BAD_REQUEST);
  }

  public FieldErrorResponse(String defaultMessage, String field) {
    setStatus(HttpStatus.BAD_REQUEST);
    setMessage(defaultMessage);
    this.field = field;
  }

  public void setDefaultMessage(String defaultMessage) {
    setMessage(defaultMessage);
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @Override
  public boolean equals(Object other) {
    if (other.getClass() != FieldErrorResponse.class) {
      return false;
    }
    FieldErrorResponse fieldErrorResponse = (FieldErrorResponse) other;
    if (fieldErrorResponse.getField().equals(field) && fieldErrorResponse.getMessage().equals(getMessage())) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "[" + field + "] (" + getMessage() + ")";
  }
}
