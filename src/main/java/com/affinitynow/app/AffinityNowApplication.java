package com.affinitynow.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.affinityNow.app"})  // scan JPA entities
public class AffinityNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(AffinityNowApplication.class, args);
	}

}
