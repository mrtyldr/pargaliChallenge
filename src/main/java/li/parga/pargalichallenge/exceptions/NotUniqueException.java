package li.parga.pargalichallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotUniqueException extends RuntimeException
{
    public NotUniqueException(String message) {
        super(message);
    }
}
