package li.parga.pargalichallenge.exceptions;


import li.parga.pargalichallenge.core.utilities.results.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {



    @ExceptionHandler
    ResponseEntity<Object> handleExceptions(Exception exception) {
        ErrorResult dataResult = new ErrorResult(exception.getMessage());
        log.error("UNKNOWN ERROR: ", exception);
        return new ResponseEntity<>(dataResult, HttpStatus.INTERNAL_SERVER_ERROR);

    }

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

    @ExceptionHandler(NegativeBalanceException.class)
    ResponseEntity<Object> handleNegativeBalance(NegativeBalanceException exception) {
        ErrorResult dataResult = new ErrorResult(exception.getMessage());

        return new ResponseEntity<>(dataResult, HttpStatus.BAD_REQUEST);
    }
}
