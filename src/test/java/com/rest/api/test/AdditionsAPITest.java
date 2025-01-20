package com.rest.api.test; 
import java.util.logging.Logger; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.impl.client.HttpClients; 
public class AdditionsAPITest { 
    private static final Logger logger = Logger.getLogger(AdditionsAPITest.class.getName()); 
    private static final String BASE_URL = "http://localhost:2222/add"; 
    public static void main(String[] args) { 
        testAddition(5, 10); 
        testAddition(0, 0); 
        testAddition(-5, 5); 
        testAddition(100, 200); 
    } 
    private static void testAddition(int addend1, int addend2) { 
        String url = BASE_URL + "?addend1=" + addend1 + "&addend2=" + addend2; 
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) { 
            HttpGet request = new HttpGet(url); 
            HttpResponse response = httpClient.execute(request); 
            if (response.getStatusLine().getStatusCode() == 200) { 
                logger.info("Response for addend1=" + addend1 + " & addend2=" + addend2 + ": " + 
                            new java.io.BufferedReader(new java.io.InputStreamReader(response.getEntity().getContent())).lines().collect(java.util.stream.Collectors.joining("\n"))); 
            } else { 
                logger.warning("Failed to call addition API: " + response.getStatusLine().getStatusCode()); 
            } 
        } catch (Exception e) { 
            logger.severe("Exception occurred while calling addition API: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
} 