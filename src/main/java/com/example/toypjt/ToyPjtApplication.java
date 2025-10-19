package com.example.toypjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToyPjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyPjtApplication.class, args);
	}

}
