package com.hackaton.hackatondonatessystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class HackatonDonatessystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackatonDonatessystemApplication.class, args);
	}

}
