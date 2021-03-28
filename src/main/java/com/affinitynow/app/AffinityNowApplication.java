package com.affinitynow.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = {"com.affinityNow.app"})  // scan JPA entities
public class AffinityNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(AffinityNowApplication.class, args);
	}

}
