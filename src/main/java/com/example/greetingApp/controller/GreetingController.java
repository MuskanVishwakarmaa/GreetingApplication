package com.example.greetingApp.controller;

import com.example.greetingApp.GreetingMessage;
import com.example.greetingApp.services.GreetingService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // UC 4 - Save a Greeting
    @PostMapping
    public GreetingMessage createGreeting(@RequestParam String message) {
        return greetingService.saveGreeting(message);
    }

    // UC 5 - Get a Greeting by ID
    @GetMapping("/{id}")
    public Optional<GreetingMessage> getGreetingById(@PathVariable Long id) {
        return greetingService.findGreetingById(id);
    }
}
