package cinema.model.response;

public record Ticket(
        int row,
        int column,
        int price
) {
    public Ticket(Seat seat, int price) {
        this(seat.row(), seat.column(), price);
    }
}
