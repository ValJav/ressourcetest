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
        return "Hello, user with ADMIN role.";
    }

    @GetMapping("/hellostructure")
    public String hellostructure() {
        return "Hello, user with STR or STRUCTURE role..";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }
}
