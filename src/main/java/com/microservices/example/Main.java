package com.microservices.example;

import com.microservices.example.registration.EurekaServer;
import com.microservices.example.rest.addition.AdditionServer;
import com.microservices.example.rest.subtraction.SubtractionServer;
import com.microservices.example.web.WebServer;

public class Main {
	public static void main(String[] args) {

		String serverName = "";

		switch (args.length) {
		case 2:
			System.setProperty("server.port", args[1]);
		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			return;
		}

		if (serverName.equals("eureka")) {//Registry Server
			EurekaServer.main(args);
		} else if (serverName.equals("addition")) {//Addition server 
			AdditionServer.main(args);
		} else if (serverName.equals("subtraction")) {//Subtraction server 
			SubtractionServer.main(args);
		} else if (serverName.equals("web")) {//Web Server 
			WebServer.main(args);
		} else {
			System.out.println("Unknown server type: " + serverName);
		}
	}
}
