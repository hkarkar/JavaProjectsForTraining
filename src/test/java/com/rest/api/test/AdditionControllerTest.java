package com.rest.api.test; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.client.RestTemplate; 
import static org.junit.jupiter.api.Assertions.assertEquals; 
public class AdditionControllerTest { 
    private RestTemplate restTemplate; 
    private String baseUrl; 
    @BeforeEach 
    public void setUp() { 
        restTemplate = new RestTemplate(); 
        baseUrl = "http://localhost:2222/add"; // URL for the addition service 
    } 
    @Test 
    public void testAdditionOfPositiveNumbers() { 
        String response = restTemplate.getForObject(baseUrl + "?addend1=3&addend2=5", String.class); 
        assertEquals("{\"addend1\":\"3\", \"addend2\":\"5\", \"sum\": \"8\"}", response); 
    } 
    @Test 
    public void testAdditionOfNegativeNumbers() { 
        String response = restTemplate.getForObject(baseUrl + "?addend1=-3&addend2=-5", String.class); 
        assertEquals("{\"addend1\":\"-3\", \"addend2\":\"-5\", \"sum\": \"-8\"}", response); 
    } 
    @Test 
    public void testAdditionOfPositiveAndNegative() { 
        String response = restTemplate.getForObject(baseUrl + "?addend1=3&addend2=-5", String.class); 
        assertEquals("{\"addend1\":\"3\", \"addend2\":\"-5\", \"sum\": \"-2\"}", response); 
    } 
    @Test 
    public void testAdditionWithMaxIntegerValues() { 
        String response = restTemplate.getForObject(baseUrl + "?addend1=2147483647&addend2=1", String.class); 
        assertEquals("{\"addend1\":\"2147483647\", \"addend2\":\"1\", \"sum\": \"2147483648\"}", response); 
    } 
    @Test 
    public void testAdditionWithMinIntegerValues() { 
        String response = restTemplate.getForObject(baseUrl + "?addend1=-2147483648&addend2=-1", String.class); 
        assertEquals("{\"addend1\":\"-2147483648\", \"addend2\":\"-1\", \"sum\": \"-2147483649\"}", response); 
    } 
} 