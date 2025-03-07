package com.example.greetingApp.controller;

import com.example.greetingApp.GreetingMessage;
import com.example.greetingApp.services.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // UC1: Return JSON for different HTTP Methods
    @GetMapping("/test")
    public String getGreetingTest() {
        return "{\"message\": \"Hello, World!\"}";
    }

    @PostMapping("/test")
    public String postGreetingTest() {
        return "{\"message\": \"Greeting Created!\"}";
    }

    @PutMapping("/test")
    public String putGreetingTest() {
        return "{\"message\": \"Greeting Updated!\"}";
    }

    @DeleteMapping("/test")
    public String deleteGreetingTest() {
        return "{\"message\": \"Greeting Deleted!\"}";
    }

    // UC2: Get Greeting using Service Layer
    @GetMapping
    public String getGreeting() {
        return "{\"message\": \"" + greetingService.getGreetingMessage() + "\"}";
    }

    // UC3: Get Greeting with Name Parameters
    @GetMapping("/custom")
    public String getPersonalizedGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return "{\"message\": \"" + greetingService.getGreetingMessage(firstName, lastName) + "\"}";
    }

    // UC4: Save Greeting in Repository
    @PostMapping
    public GreetingMessage addGreeting(@RequestParam String message) {
        return greetingService.saveGreeting(message);
    }

    // UC5: Find Greeting by ID
    @GetMapping("/{id}")
    public Optional<GreetingMessage> getGreetingById(@PathVariable Long id) {
        return greetingService.findGreetingById(id);
    }

    // UC6: List All Greetings
    @GetMapping("/all")
    public List<GreetingMessage> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
    // UC7: Update a Greeting by ID
    @PutMapping("/{id}")
    public ResponseEntity<GreetingMessage> updateGreeting(@PathVariable Long id, @RequestParam String message) {
        return greetingService.updateGreeting(id, message)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
