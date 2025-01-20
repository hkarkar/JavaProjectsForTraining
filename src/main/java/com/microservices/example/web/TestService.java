package com.microservices.example.web; 
import com.rest.api.test.ArithmeticIntegrationTest; 
import java.io.ByteArrayOutputStream; 
import java.io.PrintStream; 
import java.util.logging.Logger; 
import org.springframework.stereotype.Service; 
@Service 
public class TestService { 
    protected Logger logger = Logger.getLogger(TestService.class.getName()); 
    public String runAllTests() { 
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
        PrintStream printStream = new PrintStream(outputStream); 
        PrintStream originalOut = System.out; 
        try { 
            System.setOut(printStream); 
            logger.info("Running all integration tests."); 
            ArithmeticIntegrationTest.main(new String[]{}); // Run the integration tests 
            System.out.flush(); 
            logger.info("Integration tests executed successfully."); 
        } catch (Exception e) { 
            logger.severe("Error occurred while running integration tests: " + e.getMessage()); 
            logger.severe("Stack Trace: " + e.getStackTrace()); 
        } finally { 
            System.setOut(originalOut); 
        } 
        return outputStream.toString(); // Return the captured output 
    } 
} 