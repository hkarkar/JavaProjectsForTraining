package com.rest.api.test; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.logging.Logger; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.DefaultHttpClient; 
public class TestMyRESTAPI { 
	@SuppressWarnings("deprecation") 
	DefaultHttpClient httpClient  = null; 
	HttpResponse response = null; 
	String serviceURL = null; 
	private static final Logger logger = Logger.getLogger(TestMyRESTAPI.class.getName()); 
	public TestMyRESTAPI(String serviceURL) { 
		httpClient = new DefaultHttpClient(); 
		this.serviceURL = serviceURL; 
	} 
	public String executeGETMethod() { 
		StringBuffer sb = null; 
		try { 
			HttpGet getRequest = new HttpGet(serviceURL); 
			getRequest.addHeader("accept", "application/json"); 
			response = httpClient.execute(getRequest); 
			if (response.getStatusLine().getStatusCode() != 200) { 
				logger.severe("Failed : HTTP error code : " + response.getStatusLine().getStatusCode()); 
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode()); 
			} 
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()))); 
			String output; 
			sb = new StringBuffer(); 
			logger.info("Output from Server .... \n"); 
			while ((output = br.readLine()) != null) { 
				sb.append(output); 
			} 
			httpClient.getConnectionManager().shutdown(); 
		} catch (ClientProtocolException e) { 
			logger.severe("ClientProtocolException occurred: " + e.getMessage()); 
			e.printStackTrace(); 
		} catch (IOException e) { 
			logger.severe("IOException occurred: " + e.getMessage()); 
			e.printStackTrace(); 
		} 
		return sb.toString(); 
	} 
	public static void main(String[] args) { 
		// Existing addition service test 
		TestMyRESTAPI additionTest = new TestMyRESTAPI("http://localhost:2222/add?addend1=1111&addend2=1123"); 
		String additionResponse = additionTest.executeGETMethod(); 
		logger.info("Addition Response: " + additionResponse); 
		// New subtraction service test 
		TestMyRESTAPI subtractionTest = new TestMyRESTAPI("http://localhost:3333/subtract?minuend=12&subtrahend=4"); 
		String subtractionResponse = subtractionTest.executeGETMethod(); 
		logger.info("Subtraction Response: " + subtractionResponse); 
	} 
}