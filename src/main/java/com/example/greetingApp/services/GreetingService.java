package com.example.greetingApp.services;

import com.example.greetingApp.GreetingMessage;
import com.example.greetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // UC2: Simple Greeting
    public String getGreetingMessage() {
        return "Hello, World!";
    }

    // UC3: Greeting with Name Handling
    public String getGreetingMessage(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            return "Hello, " + firstName + "!";
        } else if (lastName != null) {
            return "Hello, " + lastName + "!";
        } else {
            return "Hello, World!";
        }
    }

    // UC4: Save Greeting Message
    public GreetingMessage saveGreeting(String message) {
        return greetingRepository.save(new GreetingMessage(message));
    }

    // UC5: Find Greeting by ID
    public Optional<GreetingMessage> findGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    // UC6: List all Greeting Messages
    public List<GreetingMessage> getAllGreetings() {
        return greetingRepository.findAll();
    }
}
