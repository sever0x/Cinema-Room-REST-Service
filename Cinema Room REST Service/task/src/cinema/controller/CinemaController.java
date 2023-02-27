package cinema.controller;

import cinema.model.response.*;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/seats")
    public Cinema cinemaInfo() {
        return cinemaService.getCinemaInfo();
    }

    @PostMapping("/purchase")
    public SoldTicket purchaseTicket(@RequestBody Seat seat) {
        return cinemaService.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody SoldTicket soldTicket) {
        return cinemaService.returnTicket(soldTicket.token());
    }

    @PostMapping("/stats")
    public Stats stats(@RequestParam(required = false) String password) {
        return cinemaService.getStats(password);
    }
}
