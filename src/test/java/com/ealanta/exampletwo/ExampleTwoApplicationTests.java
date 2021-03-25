package com.ealanta.exampletwo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.*;

@SpringBootTest({"spring.main.allow-bean-definition-overriding=true"})
@Import(ExampleTwoApplicationTests.TestConfig.class)
class ExampleTwoApplicationTests {

	@Test
	void contextLoads() {
		var time = new Time(Instant.now());
		System.out.printf("Time is %s%n",time);
	}

	@TestConfiguration
	public static class TestConfig {
		@Bean
		public Clock clock(ZoneId zoneId) {
			return Clock.system(zoneId);
		}
	}


}
