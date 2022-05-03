package com.rampup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class RampUpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RampUpServerApplication.class, args);
		System.out.println("Hello World");
	}
}
