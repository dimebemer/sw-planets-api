package com.starwars.swplanetsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SwPlanetsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwPlanetsApiApplication.class, args);
	}

	@Bean
	WebClient swapiClient() {
		return WebClient.create("https://swapi.co/api");
	}

}
