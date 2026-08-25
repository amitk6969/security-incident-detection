package com.projectsecurity.automation1.service;

import com.projectsecurity.automation1.entity.SecurityEvent;
import com.projectsecurity.automation1.repository.SecurityEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityEventService {

    private final SecurityEventRepository repository;

    public SecurityEventService(SecurityEventRepository repository) {
        this.repository = repository;
    }

    public List<SecurityEvent> getAllEvents() {
        return repository.findAll();
    }

    public SecurityEvent getEventById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Security event not found: " + id));
    }

    public long getEventCount() {
        return repository.count();
    }
}