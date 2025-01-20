package com.microservices.example.web; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import java.util.logging.Logger; 
@Controller 
public class TestController { 
    @Autowired 
    private TestService testService; 
    protected Logger logger = Logger.getLogger(TestController.class.getName()); 
    @RequestMapping("/run-tests") 
    public String runTests(Model model) { 
        try { 
            logger.info("Triggering test execution."); 
            String results = testService.runAllTests(); 
            model.addAttribute("results", results); 
            logger.info("Test execution completed successfully."); 
        } catch (Exception e) { 
            logger.severe("Error occurred while running tests: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
            model.addAttribute("results", "Error occurred while running tests. Please check the logs."); 
        } 
        return "testResults"; 
    } 
} 