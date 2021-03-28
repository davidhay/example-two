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
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:git.properties")
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
	public GitInfo gitInfo(@Value("${git.commit.id.abbrev:N/A}") String gitSha){
		return new GitInfo(gitSha);
	}

	@Bean
	public CommandLineRunner runner(GitInfo gitInfo) {
		return (String... args) -> {
			String msg = String.format("The Git Commit Hash is [%s]",gitInfo.getGitSha());
			LOG.info(msg);
		};
	}
}
