package com.ealanta.exampletwo.mvc;

import com.ealanta.exampletwo.Time;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@TestPropertySource(properties = {"spring.main.allow-bean-definition-overriding=true"})
@Import(TimeControllerMvcTest.TestConfig.class)
public class TimeControllerMvcTest {

    public static final String TEST_TIME = "2021-03-25T14:17:43Z";

    @Autowired
    Clock clock;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private Instant start;

    @BeforeEach
    public void setup() {
        this.start = clock.instant();
    }

    @Test
    public void test() throws Exception {
        MvcResult mvc = mockMvc.perform(get("/api/time"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.time").value(TEST_TIME))
                .andReturn();
        String json = mvc.getResponse().getContentAsString();
        Time time = mapper.readValue(json, Time.class);
        assertEquals(time.getTime(), start);
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public Clock clock(ZoneId zoneId) {
            return Clock.fixed(Instant.parse(TEST_TIME), zoneId);
        }
    }

}
