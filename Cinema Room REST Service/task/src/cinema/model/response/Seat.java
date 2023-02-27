package cinema.model.response;

public record Seat(
        int row,
        int column
) {
    public Seat(Ticket ticket) {
        this(ticket.row(), ticket.column());
    }
}