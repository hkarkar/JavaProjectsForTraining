package com.rest.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestMyRESTAPI {

	@SuppressWarnings("deprecation")
	DefaultHttpClient httpClient  = null;
	HttpResponse response = null;
	String serviceURL = null;
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
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			sb = new StringBuffer();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
				sb.append(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		
		return sb.toString();
	}
	public static void main(String[] args) {
		TestMyRESTAPI tmr = new TestMyRESTAPI("http://localhost:2222/add?addend1=1111&addend2=1123");
		//TestMyRESTAPI tmr = new TestMyRESTAPI("http://localhost:3333/subtract?subtract?minuend=12&subtrahend=14");
		//TestMyRESTAPI tmr = new TestMyRESTAPI("http://localhost:2222/add?addend1=10&addend2=11");
		//TestMyRESTAPI tmr = new TestMyRESTAPI("http://localhost:4444/add?addend1=11&addend2=12");
		//TestMyRESTAPI tmr = new TestMyRESTAPI("https://www.msn.com/en-in");
		//http://localhost:8765/api/accounts
		//TestMyRESTAPI tmr = new TestMyRESTAPI("http://localhost:8765/api/accounts");
		String response  =tmr.executeGETMethod();
		System.out.println("===================================");
		System.out.println(response);
		System.out.println("===================================");
		
	}

}
