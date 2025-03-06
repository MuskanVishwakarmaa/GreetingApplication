package com.example.greetingApp.services;

import com.example.greetingApp.GreetingMessage;
import com.example.greetingApp.repository.GreetingRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public GreetingMessage saveGreeting(String message) {
        GreetingMessage greetingMessage = new GreetingMessage(message);
        return greetingRepository.save(greetingMessage);
    }

    public Optional<GreetingMessage> findGreetingById(Long id) {
        return greetingRepository.findById(id);
    }
}