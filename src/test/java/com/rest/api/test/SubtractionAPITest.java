package com.rest.api.test; 
import java.util.logging.Logger; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.impl.client.HttpClients; 
public class SubtractionAPITest { 
    private static final Logger logger = Logger.getLogger(SubtractionAPITest.class.getName()); 
    private static final String BASE_URL = "http://localhost:3333/subtract"; 
    public static void main(String[] args) { 
        testSubtraction(10, 5); 
        testSubtraction(0, 0); 
        testSubtraction(5, 10); 
        testSubtraction(-5, -5); 
    } 
    private static void testSubtraction(int minuend, int subtrahend) { 
        String url = BASE_URL + "?minuend=" + minuend + "&subtrahend=" + subtrahend; 
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) { 
            HttpGet request = new HttpGet(url); 
            HttpResponse response = httpClient.execute(request); 
            if (response.getStatusLine().getStatusCode() == 200) { 
                logger.info("Response for minuend=" + minuend + " & subtrahend=" + subtrahend + ": " + 
                            new java.io.BufferedReader(new java.io.InputStreamReader(response.getEntity().getContent())).lines().collect(java.util.stream.Collectors.joining("\n"))); 
            } else { 
                logger.warning("Failed to call subtraction API: " + response.getStatusLine().getStatusCode()); 
            } 
        } catch (Exception e) { 
            logger.severe("Exception occurred while calling subtraction API: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
} 