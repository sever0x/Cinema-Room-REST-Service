package cinema.model.response;

public record SoldTicket(
        String token,
        Ticket ticket
) {
}
