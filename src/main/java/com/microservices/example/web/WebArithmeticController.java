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
    protected Logger logger = Logger.getLogger(WebArithmeticController.class.getName()); 
    public WebArithmeticController(WebAdditionService additionService, WebSubtractionService subtractionService) { 
        this.additionService = additionService; 
        this.subtractionService = subtractionService; 
    } 
    // Handle addition requests 
    @RequestMapping("/add") 
    public String doAdd(@RequestParam(defaultValue="0") String addend1, 
                        @RequestParam(defaultValue="0") String addend2, 
                        Model model) { 
        try { 
            String sum = additionService.add(addend1, addend2); 
            logger.info("Sum: " + sum); 
            model.addAttribute("json", sum); 
        } catch (Exception e) { 
            logger.severe("Error during addition: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("json", "Error occurred during addition"); 
        } 
        return "sum"; // Return to the sum.html template with result 
    } 
    // Handle subtraction requests 
    @RequestMapping("/subtract") 
    public String doSubtract(@RequestParam(defaultValue="0") String minuend, 
                             @RequestParam(defaultValue="0") String subtrahend, 
                             Model model) { 
        try { 
            String difference = subtractionService.subtract(minuend, subtrahend); 
            logger.info("Difference: " + difference); 
            model.addAttribute("json", difference); 
        } catch (Exception e) { 
            logger.severe("Error during subtraction: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("json", "Error occurred during subtraction"); 
        } 
        return "difference"; // Return to the difference.html template with result 
    } 
    // New method to show the addition form 
    @RequestMapping("/addition") 
    public String showAdditionForm(Model model) { 
        return "addition"; // Returns the addition.html template 
    } 
    // New method to show the subtraction form 
    @RequestMapping("/subtraction") 
    public String showSubtractionForm(Model model) { 
        return "subtraction"; // Returns the subtraction.html template 
    } 
}