package com.inserm.gdi.ressourcetest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World! This is a protected resource.";
    }
    
    @GetMapping("/helloadmin")
    public String helloAdmin() {
        return "Hello, ADMIIIIN.";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }

    @GetMapping("/protected")
    public ResponseEntity<Map<String, Object>> getProtectedResource(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "This is a protected resource");
        response.put("username", authentication.getName());
        response.put("authorities", authentication.getAuthorities());
        
        return ResponseEntity.ok(response);
    }
}
