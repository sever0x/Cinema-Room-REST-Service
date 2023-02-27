package cinema.exception;

public class WrongPasswordException extends RuntimeException {

    public static final String MESSAGE = "The password is wrong!";

    public WrongPasswordException() {
        super(MESSAGE);
    }
}
