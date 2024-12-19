package com.microservices.example.rest.subtraction;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class SubtractionServer {
	protected Logger logger = Logger.getLogger(SubtractionServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "subtraction-server");//read from subtraction-server.properties file

		SpringApplication.run(SubtractionServer.class, args);
	}
}
