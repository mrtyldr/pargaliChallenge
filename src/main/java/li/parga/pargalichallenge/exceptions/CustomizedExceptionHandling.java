package li.parga.pargalichallenge.exceptions;

import li.parga.pargalichallenge.core.utilities.results.ErrorDataResult;
import li.parga.pargalichallenge.core.utilities.results.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Object> handleExceptions(NotFoundException exception) {
        ErrorResult dataResult = new ErrorResult(exception.getMessage());
        return new ResponseEntity<>(dataResult, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NotUniqueException.class)
    ResponseEntity<Object> handleNotUniqueExceptions(NotUniqueException exception) {
        ErrorResult dataResult = new ErrorResult(exception.getMessage());

        return new ResponseEntity<>(dataResult, HttpStatus.BAD_REQUEST);
    }


}
