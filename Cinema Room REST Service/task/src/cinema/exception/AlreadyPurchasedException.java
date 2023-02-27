package cinema.exception;

public class AlreadyPurchasedException extends RuntimeException {

    public static final String MESSAGE = "The ticket has been already purchased!";

    public AlreadyPurchasedException() {
        super(MESSAGE);
    }
}
