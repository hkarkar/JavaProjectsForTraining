package com.rest.api.test; 
import java.util.logging.Logger; 
public class ArithmeticIntegrationTest { 
    private static final Logger logger = Logger.getLogger(ArithmeticIntegrationTest.class.getName()); 
    private static final String ADDITION_URL = "http://localhost:2222/add"; 
    private static final String SUBTRACTION_URL = "http://localhost:3333/subtract"; 
    public static void main(String[] args) { 
        // Test addition 
        testAddition(5, 10); 
        testAddition(0, 0); 
        testAddition(-5, 5); 
        testAddition(100, 200); 
        // Test subtraction 
        testSubtraction(10, 5); 
        testSubtraction(0, 0); 
        testSubtraction(5, 10); 
        testSubtraction(-5, -5); 
    } 
    private static void testAddition(int addend1, int addend2) { 
        String url = ADDITION_URL + "?addend1=" + addend1 + "&addend2=" + addend2; 
        try { 
            TestMyRESTAPI testApi = new TestMyRESTAPI(url); 
            String response = testApi.executeGETMethod(); 
            logger.info("Addition Response for addend1=" + addend1 + " & addend2=" + addend2 + ": " + response); 
        } catch (Exception e) { 
            logger.severe("Failed to call addition API: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
    private static void testSubtraction(int minuend, int subtrahend) { 
        String url = SUBTRACTION_URL + "?minuend=" + minuend + "&subtrahend=" + subtrahend; 
        try { 
            TestMyRESTAPI testApi = new TestMyRESTAPI(url); 
            String response = testApi.executeGETMethod(); 
            logger.info("Subtraction Response for minuend=" + minuend + " & subtrahend=" + subtrahend + ": " + response); 
        } catch (Exception e) { 
            logger.severe("Failed to call subtraction API: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
} 