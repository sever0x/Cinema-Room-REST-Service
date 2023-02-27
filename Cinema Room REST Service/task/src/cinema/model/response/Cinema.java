package cinema.model.response;

import java.util.List;

public record Cinema(

        int totalRows,

        int totalColumns,

        List<Ticket> availableSeats
) {
}
