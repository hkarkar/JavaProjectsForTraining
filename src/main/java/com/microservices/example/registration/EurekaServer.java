package com.microservices.example.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "eureka-server");//read all configuration properties from the file eureka-server.properties

		SpringApplication.run(EurekaServer.class, args);
	}
}
