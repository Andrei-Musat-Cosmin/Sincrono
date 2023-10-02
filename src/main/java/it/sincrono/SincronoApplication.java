package it.sincrono;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronoApplication {

	private static final Logger LOGGER = LogManager.getLogger(SincronoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SincronoApplication.class, args);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOGGER.info("Shutting down application...");
			LogManager.shutdown(); // This will flush and close all loggers
		}));

	}

}
