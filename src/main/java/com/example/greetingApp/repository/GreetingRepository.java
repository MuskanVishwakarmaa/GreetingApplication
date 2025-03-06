package com.example.greetingApp.repository;

import com.example.greetingApp.GreetingMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<GreetingMessage, Long> {
}
