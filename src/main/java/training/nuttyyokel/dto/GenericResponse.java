package training.nuttyyokel.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author DrMarcell
 */
public class GenericResponse {

  private String message;
  private HttpStatus status;

  public GenericResponse() {
    status = HttpStatus.OK;
  }

  public GenericResponse(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public ResponseEntity<Object> build() {
    return new ResponseEntity(this, status);
  }
}
