package training.nuttyyokel.dto;

import org.springframework.http.HttpStatus;

/**
 * @author DrMarcell
 */
public class ResponseDTO {
  private HttpStatus status;
  private String message;

  public ResponseDTO() {
  }

  public ResponseDTO(String message, HttpStatus status) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
