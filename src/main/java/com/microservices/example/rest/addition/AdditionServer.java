package com.microservices.example.rest.addition;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan
public class AdditionServer {

	protected Logger logger = Logger.getLogger(AdditionServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "addition-server");// Read properties named addition-server.properties

		SpringApplication.run(AdditionServer.class, args);
	}
}
