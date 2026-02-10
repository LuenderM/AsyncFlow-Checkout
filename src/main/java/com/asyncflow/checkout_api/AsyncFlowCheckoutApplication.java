package com.asyncflow.checkout_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.asyncflow")
public class AsyncFlowCheckoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncFlowCheckoutApplication.class, args);
		System.out.println("AsyncFlowCheckoutApplication started...");
	}

}
