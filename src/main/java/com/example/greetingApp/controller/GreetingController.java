package com.example.greetingApp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String getGreeting() {
        return "{\"message\": \"Hello, World!\"}";
    }

    @PostMapping
    public String postGreeting() {
        return "{\"message\": \"Greeting Created!\"}";
    }

    @PutMapping
    public String putGreeting() {
        return "{\"message\": \"Greeting Updated!\"}";
    }

    @DeleteMapping
    public String deleteGreeting() {
        return "{\"message\": \"Greeting Deleted!\"}";
    }
}