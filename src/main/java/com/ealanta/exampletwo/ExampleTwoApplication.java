package com.ealanta.exampletwo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ExampleTwoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ExampleTwoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExampleTwoApplication.class, args);
	}

	@Bean
	public ZoneId zoneId(@Value("${app.zone.id:UTC}") String zoneName) {
		return ZoneId.of(zoneName);
	}

	@Bean
	public Clock clock(ZoneId zoneId) {
		return Clock.system(zoneId);
	}

	@Bean
	public CommandLineRunner runner(@Value("${info.git.sha}") String gitSha) {
		return (String... args) -> {
			String msg = String.format("OLD : The Git Commit Hash is [%s]",gitSha);
			LOG.info(msg);

			String systemShortSha = System.getProperty("SHORT_SHA");
			String msg1 = String.format("NEW : SHORT SHA from System.getProperty()[%s]",systemShortSha);
			LOG.info(msg1);

			String envShortSha = System.getenv("SHORT_SHA");
			String msg2 = String.format("NEW : SHORT SHA from System.getenv()[%s]",envShortSha);
			LOG.info(msg2);
		};
	}
}
