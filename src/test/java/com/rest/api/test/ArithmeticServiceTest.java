package com.rest.api.test; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.util.logging.Logger; 
public class ArithmeticServiceTest { 
    private static final String ADDITION_SERVICE_URL = "http://localhost:2222/add?addend1=5&addend2=3"; 
    private static final String SUBTRACTION_SERVICE_URL = "http://localhost:3333/subtract?minuend=10&subtrahend=4"; 
    private static final Logger logger = Logger.getLogger(ArithmeticServiceTest.class.getName()); 
    public static void main(String[] args) { 
        try { 
            // Call addition service 
            String additionResponse = callService(ADDITION_SERVICE_URL); 
            logger.info("Response from Addition Service: " + additionResponse); 
            // Call subtraction service 
            String subtractionResponse = callService(SUBTRACTION_SERVICE_URL); 
            logger.info("Response from Subtraction Service: " + subtractionResponse); 
        } catch (IOException e) { 
            logger.severe("Error occurred while calling services: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
    private static String callService(String serviceUrl) throws IOException { 
        URL url = new URL(serviceUrl); 
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
        connection.setRequestMethod("GET"); 
        connection.setRequestProperty("Accept", "application/json"); 
        if (connection.getResponseCode() != 200) { 
            logger.severe("Failed : HTTP error code : " + connection.getResponseCode()); 
            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode()); 
        } 
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
        StringBuilder response = new StringBuilder(); 
        String line; 
        while ((line = br.readLine()) != null) { 
            response.append(line); 
        } 
        br.close(); 
        return response.toString(); 
    } 
}