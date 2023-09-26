package it.sincrono;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronoApplication {

	private static final Logger logger = LogManager.getLogger(SincronoApplication.class);

	public static void main(String[] args) {
		logger.log(Level.INFO, "Start application SincronoApplication.");

		SpringApplication.run(SincronoApplication.class, args);

//		logger.log(Level.INFO, "Closing application SincronoApplication and logger...");
//		LogManager.shutdown();

	}

}
