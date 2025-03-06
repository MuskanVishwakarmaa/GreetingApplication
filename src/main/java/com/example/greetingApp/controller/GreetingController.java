package com.example.greetingApp.controller;

import com.example.greetingApp.services.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public String getGreeting(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        return "{\"message\": \"" + greetingService.getGreetingMessage(firstName, lastName) + "\"}";
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