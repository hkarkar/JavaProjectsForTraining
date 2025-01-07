package com.rest.api.test; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.client.RestTemplate; 
import static org.junit.jupiter.api.Assertions.assertEquals; 
public class SubtractionControllerTest { 
    private RestTemplate restTemplate; 
    private String baseUrl; 
    @BeforeEach 
    public void setUp() { 
        restTemplate = new RestTemplate(); 
        baseUrl = "http://localhost:3333/subtract"; // URL for the subtraction service 
    } 
    @Test 
    public void testSubtractionOfPositiveNumbers() { 
        String response = restTemplate.getForObject(baseUrl + "?minuend=10&subtrahend=5", String.class); 
        assertEquals("{\"minuend\":\"10\", \"subtrahend\":\"5\", \"difference\": \"5\"}", response); 
    } 
    @Test 
    public void testSubtractionOfNegativeNumbers() { 
        String response = restTemplate.getForObject(baseUrl + "?minuend=-10&subtrahend=-5", String.class); 
        assertEquals("{\"minuend\":\"-10\", \"subtrahend\":\"-5\", \"difference\": \"-5\"}", response); 
    } 
    @Test 
    public void testSubtractionOfPositiveAndNegative() { 
        String response = restTemplate.getForObject(baseUrl + "?minuend=10&subtrahend=-5", String.class); 
        assertEquals("{\"minuend\":\"10\", \"subtrahend\":\"-5\", \"difference\": \"15\"}", response); 
    } 
    @Test 
    public void testSubtractionWithMaxIntegerValues() { 
        String response = restTemplate.getForObject(baseUrl + "?minuend=2147483647&subtrahend=-1", String.class); 
        assertEquals("{\"minuend\":\"2147483647\", \"subtrahend\":\"-1\", \"difference\": \"2147483648\"}", response); 
    } 
    @Test 
    public void testSubtractionWithMinIntegerValues() { 
        String response = restTemplate.getForObject(baseUrl + "?minuend=-2147483648&subtrahend=1", String.class); 
        assertEquals("{\"minuend\":\"-2147483648\", \"subtrahend\":\"1\", \"difference\": \"-2147483649\"}", response); 
    } 
} 