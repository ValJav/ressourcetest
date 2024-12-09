package com.inserm.gdi.ressourcetest.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtValidation {
    
    private final RestTemplate restTemplate;

	public JwtValidation(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String someRestCall(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity<String> request = new HttpEntity<>("", headers);
		String url = "http://localhost:8000/api/auth/validate";
		return restTemplate.postForObject(url, request, String.class);
	}

}