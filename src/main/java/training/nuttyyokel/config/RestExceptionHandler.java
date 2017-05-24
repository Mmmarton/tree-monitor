package training.nuttyyokel.config;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import training.nuttyyokel.controller.TreeController;
import training.nuttyyokel.dto.FieldErrorResponse;
import training.nuttyyokel.dto.GenericResponse;
import training.nuttyyokel.service.exception.InvalidTreeIdException;

import java.lang.reflect.Type;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

  @Autowired
  private ModelMapper mapper;

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Object> handleCustomException(HttpRequestMethodNotSupportedException exception) {
    return new GenericResponse("wrong request method", HttpStatus.BAD_REQUEST).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    return new ResponseEntity(mapList(fieldErrors), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(DataIntegrityViolationException exception) {
    ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception.getCause();
    FieldErrorResponse response = new FieldErrorResponse();
    response.setMessage(TreeController.MESSAGE_INVALID_FIELD);
    response.setField(constraintViolationException.getConstraintName());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidTreeIdException.class)
  public ResponseEntity<Object> handleInvalidTreeIdException(InvalidTreeIdException exception) {
    return new GenericResponse(TreeController.MESSAGE_INVALID_ID, HttpStatus.BAD_REQUEST).build();
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleInvalidTreeIdException(EmptyResultDataAccessException exception) {
    return new GenericResponse(TreeController.MESSAGE_INVALID_ID, HttpStatus.BAD_REQUEST).build();
  }

  private List<FieldErrorResponse> mapList(List<FieldError> fieldErrors) {
    Type dataListType = new TypeToken<List<FieldErrorResponse>>() {
    }.getType();
    return mapper.map(fieldErrors, dataListType);
  }

}