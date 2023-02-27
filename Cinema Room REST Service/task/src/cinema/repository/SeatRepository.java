package cinema.repository;

import cinema.model.response.Seat;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SeatRepository {

    private Set<Seat> availableSeats = new LinkedHashSet<>();

    public List<Seat> getAvailableSeats() {
        return availableSeats.stream().toList();
    }

    public void addSeat(Seat seat) {
        availableSeats.add(seat);
    }

    public boolean exists(Seat seat) {
        return availableSeats.contains(seat);
    }

    public boolean delete(Seat seat) {
        return availableSeats.remove(seat);
    }
}
