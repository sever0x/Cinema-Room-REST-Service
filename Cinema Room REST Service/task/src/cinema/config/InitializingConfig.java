package cinema.config;

import cinema.model.response.Seat;
import cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializingConfig implements CommandLineRunner {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CinemaProperties cinemaProperties;

    @Override
    public void run(String... args) {
        for (int row = 1; row <= cinemaProperties.totalRows(); row++) {
            for (int column = 1; column <= cinemaProperties.totalColumns(); column++) {
                seatRepository.addSeat(new Seat(row, column));
            }
        }
    }
}
