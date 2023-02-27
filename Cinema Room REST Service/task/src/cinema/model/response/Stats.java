package cinema.model.response;

public record Stats(
        int currentIncome,
        int numberOfAvailableSeats,
        int numberOfPurchasedTickets
) {
}
