package com.group11.shelftalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ShelftalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelftalkApplication.class, args);
	}

	

}
