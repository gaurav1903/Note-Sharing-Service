package com.example.RateLimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class RateLimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateLimiterApplication.class, args);
	}

}
