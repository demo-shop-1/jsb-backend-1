package org.demo.shop1;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Shop1Application {

	protected static final Logger logger = Logger.getLogger(Shop1Application.class.getName());

	public static void main(String[] args) {
		logger.info("Starting web server!");
		SpringApplication.run(Shop1Application.class, args);
	}

}
