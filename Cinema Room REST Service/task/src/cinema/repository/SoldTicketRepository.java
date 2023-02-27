package cinema.repository;

import cinema.model.response.SoldTicket;
import cinema.model.response.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class SoldTicketRepository {

    Map<String, Ticket> soldTickets = new HashMap<>();

    public boolean exists(String token) {
        log.info("desired token = {}", token);
        log.info("desired ticket = {}", soldTickets.get(token));
        return soldTickets.containsKey(token);
    }

    public void add(SoldTicket soldTicket) {
        soldTickets.put(soldTicket.token(), soldTicket.ticket());
        log.info("sold tickets = {}", soldTickets);
    }

    public Ticket delete(String token) {
        log.info("sold ticket = {}", soldTickets.get(token));
        return soldTickets.remove(token);
    }

    public Map<String, Ticket> getSoldTickets() {
        return soldTickets;
    }
}
