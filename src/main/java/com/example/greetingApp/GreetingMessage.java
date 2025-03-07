package com.example.greetingApp;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class GreetingMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public GreetingMessage() {}

    public GreetingMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}