package com.microservices.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class WebServer {

	public static final String ADDITION_SERVICE_URL = "http://addition-service";

	public static final String SUBTRACTION_SERVICE_URL = "http://subtraction-service";

	//Starts the spring boot application.
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "web-server");//read properties from  the web-server.properties
		SpringApplication.run(WebServer.class, args);
	}

	//Instancing a RestTemplate Object - > used to trigger a call to the REST API 
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	//Instancing the WebAdditionService and pass url of the addition service 
	@Bean
	public WebAdditionService additionService() {
		return new WebAdditionService(ADDITION_SERVICE_URL);
	}
	
	
	//Instancing the controller.
	@Bean
	public WebArithmeticController additionController() {
		return new WebArithmeticController(additionService(), subtractionService());
	}

	//Instancing the WebSubtractionService and pass url of the subtraction service 
	@Bean
	public WebSubtractionService subtractionService() {
		return new WebSubtractionService(SUBTRACTION_SERVICE_URL);
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}
}