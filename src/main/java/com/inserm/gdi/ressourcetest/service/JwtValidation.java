package com.inserm.gdi.ressourcetest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class JwtValidation {
    
    private final RestClient restClient;

	public JwtValidation(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.baseUrl("http://localhost:8000").build();
	}

	public String someRestCall(String token) {
        System.out.println("SomeRestCall Token : " + token);
		return this.restClient.get().uri("/api/public").retrieve().body(String.class);
	}

}