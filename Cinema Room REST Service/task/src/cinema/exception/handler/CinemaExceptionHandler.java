package cinema.exception.handler;

import cinema.exception.AlreadyPurchasedException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CinemaExceptionHandler {

    @ExceptionHandler({
            AlreadyPurchasedException.class,
            SeatOutOfBoundsException.class,
            WrongTokenException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handle(Exception e) {
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO authorizationHandle(Exception e) {
        return new ErrorDTO(e.getMessage());
    }
}

record ErrorDTO(String error) {
}