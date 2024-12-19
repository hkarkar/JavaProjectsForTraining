package com.rest.api.test;
import java.io.*;
import java.security.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import javax.net.ssl.*;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;

import org.springframework.cloud.commons.httpclient.OkHttpClientFactory.TrustAllHostnames;
public class ChatGPTAPIExample {

	public static String chatGPT(String prompt) {
	       String url = "https://api.openai.com/v1/chat/completions";
	       String apiKey = "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";
	       String model = "gpt-3.5-turbo";

	       try {
	    	   ChatGPTAPIExample ce = new ChatGPTAPIExample();
	    	   ce.trustAllHosts();
	    	   TrustAllHostnames ta = new TrustAllHostnames();
	    	   
	           URL obj = new URL(url);
	           HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	           connection.setRequestMethod("POST");
	           connection.setRequestProperty("Authorization", "Bearer " + apiKey);
	           connection.setRequestProperty("Content-Type", "application/json");

	           // The request body
	           String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
	           connection.setDoOutput(true);
	           OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	           writer.write(body);
	           writer.flush();
	           writer.close();

	           // Response from ChatGPT
	           BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	           String line;

	           StringBuffer response = new StringBuffer();

	           while ((line = br.readLine()) != null) {
	               response.append(line);
	           }
	           br.close();

	           // calls the method to extract the message.
	           return extractMessageFromJSONResponse(response.toString());

	       } catch (IOException e) {
	           throw new RuntimeException(e);
	       }
	   }

	   public static String extractMessageFromJSONResponse(String response) {
	       int start = response.indexOf("content")+ 11;

	       int end = response.indexOf("\"", start);

	       return response.substring(start, end);

	   }

	   public static void main(String[] args) {

	       System.out.println(chatGPT("Tell me about Publications made by Dr. C.V.Ramanujan?"));

	   }

	   public void trustAllHosts()
	    {
	        try
	        {
	            TrustManager[] trustAllCerts = new TrustManager[]{
	                    new X509ExtendedTrustManager()
	                    {
	                        @Override
	                        public java.security.cert.X509Certificate[] getAcceptedIssuers()
	                        {
	                            return null;
	                        }

	                        @Override
	                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
	                        {
	                        }

	                        @Override
	                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
	                        {
	                        }

	                       
	                        @Override
	                        public void checkClientTrusted(java.security.cert.X509Certificate[] xcs, String string, SSLEngine ssle) throws CertificateException
	                        {

	                        }

	                        @Override
	                        public void checkServerTrusted(java.security.cert.X509Certificate[] xcs, String string, SSLEngine ssle) throws CertificateException
	                        {

	                        }

							@Override
							public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket)
									throws CertificateException {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket)
									throws CertificateException {
								// TODO Auto-generated method stub
								
							}

	                    }
	            };

	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, trustAllCerts, new java.security.SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	            // Create all-trusting host name verifier
	            HostnameVerifier allHostsValid = new  HostnameVerifier()
	            {
	                @Override
	                public boolean verify(String hostname, SSLSession session)
	                {
	                    return true;
	                }
	            };
	            // Install the all-trusting host verifier
	            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	        }
	        catch (Exception e)
	        {
	            System.out.println("Error occurred"+e.getMessage());
	        }
	    }

}


