package cinema.exception;

public class WrongTokenException extends RuntimeException {

    public static final String MESSAGE = "Wrong token!";

    public WrongTokenException() {
        super(MESSAGE);
    }
}
