package com.example.b5.controller;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HomeController {
    @GetMapping("/home")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
    
}
