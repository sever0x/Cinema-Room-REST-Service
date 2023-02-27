package cinema.config;

import cinema.model.Price;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@ConfigurationProperties(prefix = "cinema")
public record CinemaProperties(

        int totalRows,

        int totalColumns,

        int firstRows,

        Price price,

        String password
) {
    @PostConstruct
    void logLoaded() {
        log.info("props = {}", this);
    }
}
