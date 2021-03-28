package com.ealanta.exampletwo.integration;

import com.ealanta.exampletwo.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class TimeControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    Clock clock;

    private Instant start;

    @BeforeEach
    public void setup() {
        this.start = clock.instant();
    }

    @Test
    public void test() {

        FluxExchangeResult<Time> result = this.webTestClient
                .get()
                .uri("/api/time")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().is2xxSuccessful().returnResult(Time.class);

        Time time = result.getResponseBody().blockFirst(Duration.of(3, SECONDS));

        assertFalse(start.isAfter(Objects.requireNonNull(time).getTime()));
    }

}
