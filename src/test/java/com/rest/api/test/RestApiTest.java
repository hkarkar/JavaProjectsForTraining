package com.rest.api.test; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
public class RestApiTest { 
    @Test 
    public void testAdditionApi() { 
        String serviceURL = "http://localhost:2222/add?addend1=10&addend2=5"; // Adjust the port if needed 
        String expectedResponse = "{\"addend1\":\"10\", \"addend2\":\"5\", \"sum\": \"15\"}"; 
        try { 
            // Create a URL object 
            URL url = new URL(serviceURL); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Accept", "application/json"); 
            // Get the response code 
            int responseCode = connection.getResponseCode(); 
            assertEquals(200, responseCode, "Response code should be 200"); 
            // Read the response 
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
            StringBuilder response = new StringBuilder(); 
            String line; 
            while ((line = in.readLine()) != null) { 
                response.append(line); 
            } 
            in.close(); 
            // Verify the response content 
            assertEquals(expectedResponse, response.toString(), "Response should match expected JSON format"); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            fail("An IOException was thrown: " + e.getMessage()); 
        } 
    } 
    @Test 
    public void testSubtractionApi() { 
        String serviceURL = "http://localhost:3333/subtract?minuend=10&subtrahend=5"; // Adjust the port if needed 
        String expectedResponse = "{\"minuend\":\"10\", \"subtrahend\":\"5\", \"difference\": \"5\"}"; 
        try { 
            // Create a URL object 
            URL url = new URL(serviceURL); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET"); 
            connection.setRequestProperty("Accept", "application/json"); 
            // Get the response code 
            int responseCode = connection.getResponseCode(); 
            assertEquals(200, responseCode, "Response code should be 200"); 
            // Read the response 
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
            StringBuilder response = new StringBuilder(); 
            String line; 
            while ((line = in.readLine()) != null) { 
                response.append(line); 
            } 
            in.close(); 
            // Verify the response content 
            assertEquals(expectedResponse, response.toString(), "Response should match expected JSON format"); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            fail("An IOException was thrown: " + e.getMessage()); 
        } 
    } 
    public static void main(String[] args) { 
        RestApiTest test = new RestApiTest(); 
        System.out.println("Running all API tests..."); 
        try { 
            test.testAdditionApi(); 
            test.testSubtractionApi(); 
            System.out.println("All API tests completed successfully."); 
        } catch (AssertionError e) { 
            System.err.println("Test failed: " + e.getMessage()); 
        } catch (Exception e) { 
            System.err.println("An error occurred while running tests: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
}