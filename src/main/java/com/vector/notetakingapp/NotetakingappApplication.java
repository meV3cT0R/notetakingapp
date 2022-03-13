package com.vector.notetakingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotetakingappApplication {
	public static Logger logger = LoggerFactory.getLogger(NotetakingappApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(NotetakingappApplication.class, args);
	}

	@Bean
	CommandLineRunner runCommandLine() {
		return args->{
			logger.info("I am from CommandLineRunner");
		};
	}
}
