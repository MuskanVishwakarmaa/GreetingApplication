package com.example.greetingApp.controller;

import com.example.greetingApp.services.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    // ✅ UC 2: Constructor-based Dependency Injection for GreetingService
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // ✅ UC 1: Basic JSON responses for different HTTP methods
    @GetMapping("/default")
    public String getDefaultGreeting() {
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

    // ✅ UC 2: Updated GET request to return GreetingService message
    @GetMapping
    public String getGreeting() {
        return "{\"message\": \"" + greetingService.getGreetingMessage() + "\"}";
    }
}