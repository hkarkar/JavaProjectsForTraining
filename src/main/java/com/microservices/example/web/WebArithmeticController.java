package com.microservices.example.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebArithmeticController {
	@Autowired
	protected WebAdditionService additionService;

	@Autowired
	protected WebSubtractionService subtractionService;

	protected Logger logger = Logger.getLogger(WebArithmeticController.class
			.getName());

	public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService) {
		this.additionService = additionService;
		this.subtractionService = subtractionService;
	}

	//Exposing the request mapping of '/add for the http://localhost:4444/add -> to be consumed by the addition form 
	@RequestMapping("/add")
	public String doAdd(@RequestParam(defaultValue="0") String addend1,
			@RequestParam(defaultValue="0") String addend2,
			Model model) {//Form parameters are accepted as arguments.

		String sum = additionService.add(addend1, addend2);//Triggering a call to the Addition Service add method.

		logger.info("Sum: " + sum);
		model.addAttribute("json", sum);

		return "sum";
	}

	//Exposing the request mapping of '/subtract for the http://localhost:4444/subtract -> to be consumed by the subtraction form 
	@RequestMapping("/subtract")
	public String doSubtract(@RequestParam(defaultValue="0") String minuend,
			@RequestParam(defaultValue="0") String subtrahend,
			Model model) {

		String difference = subtractionService.subtract(minuend, subtrahend);

		logger.info("Difference: " + difference);
		model.addAttribute("json", difference);

		return "difference";
	}
}
