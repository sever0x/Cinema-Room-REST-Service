package cinema.service;

import cinema.config.CinemaProperties;
import cinema.exception.AlreadyPurchasedException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import cinema.model.response.*;
import cinema.repository.SeatRepository;
import cinema.repository.SoldTicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CinemaService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CinemaProperties cinemaProperties;

    @Autowired
    private SoldTicketRepository soldTicketRepository;

    public Cinema getCinemaInfo() {
        return new Cinema(
                cinemaProperties.totalRows(),
                cinemaProperties.totalColumns(),
                seatRepository.getAvailableSeats()
                        .stream().map(this::addPrice).toList()
        );
    }

    public SoldTicket purchaseTicket(Seat seat) {
        if (!validate(seat)) {
            throw new SeatOutOfBoundsException();
        }
        if (!seatRepository.exists(seat)) {
            throw new AlreadyPurchasedException();
        }

        seatRepository.delete(seat);
        String token = UUID.randomUUID().toString();
        SoldTicket soldTicket = new SoldTicket(token, addPrice(seat));
        soldTicketRepository.add(soldTicket);

        log.info("purchased ticket = {}", soldTicket);

        return soldTicket;
    }

    public ReturnedTicket returnTicket(String token) {
        if (!soldTicketRepository.exists(token)) {
            throw new WrongTokenException();
        }

        Ticket ticket = soldTicketRepository.delete(token);
        seatRepository.addSeat(new Seat(ticket));

        return new ReturnedTicket(ticket);
    }

    public Stats getStats(String password) {
        if (password == null || !password.equals(cinemaProperties.password())) {
            throw new WrongPasswordException();
        }

        return new Stats(
                calcCurrentIncome(),
                seatRepository.getAvailableSeats().size(),
                soldTicketRepository.getSoldTickets().size()
        );
    }

    private int calcCurrentIncome() {
        return soldTicketRepository.getSoldTickets().values().stream()
                .mapToInt(Ticket::price).sum();
    }

    private boolean validate(Seat seat) {
        return !(seat.row() < 1 || seat.column() < 1
                || seat.row() > cinemaProperties.totalRows()
                || seat.column() > cinemaProperties.totalColumns());
    }

    private Ticket addPrice(Seat seat) {
        return new Ticket(
                seat,
                calcPrice(seat)
        );
    }

    private int calcPrice(Seat seat) {
        return seat.row() <= cinemaProperties.firstRows()
                ? cinemaProperties.price().high()
                : cinemaProperties.price().low();
    }
}
