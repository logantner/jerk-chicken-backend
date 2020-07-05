package com.jerk.chicken;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Driver {
	public static void main(String[] args) {
		SpringApplication.run(Driver.class, args);
	}
}
