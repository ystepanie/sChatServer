package com.ideaproject.schatServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SchatServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchatServerApplication.class, args);
	}

}
