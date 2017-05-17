package training.nuttyyokel.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import training.nuttyyokel.dto.FieldErrorDTO;
import training.nuttyyokel.dto.ResponseDTO;
import training.nuttyyokel.dto.TreeDTO;
import training.nuttyyokel.model.Tree;
import training.nuttyyokel.service.exception.InvalidTreeIdException;

import java.lang.reflect.Type;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

  @Autowired
  private ModelMapper mapper;

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Object> handleCustomException(HttpRequestMethodNotSupportedException exception) {
    return new ResponseDTO("wrong request method", HttpStatus.BAD_REQUEST).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    return new ResponseEntity(mapList(fieldErrors), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidTreeIdException.class)
  public ResponseEntity<Object> handleInvalidTreeIdException(InvalidTreeIdException exception) {
    return new ResponseDTO("invalid tree id", HttpStatus.BAD_REQUEST).build();
  }

  private List<FieldErrorDTO> mapList(List<FieldError> fieldErrors) {
    Type dataListType = new TypeToken<List<FieldErrorDTO>>() {
    }.getType();
    return mapper.map(fieldErrors, dataListType);
  }

}