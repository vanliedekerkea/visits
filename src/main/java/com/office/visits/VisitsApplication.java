package com.office.visits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.office.visits.model") 
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.office.visits"})
public class VisitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitsApplication.class, args);
	}

}
