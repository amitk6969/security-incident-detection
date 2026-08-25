package com.projectsecurity.automation1.service;

import com.projectsecurity.automation1.entity.SecurityAction;
import com.projectsecurity.automation1.repository.SecurityActionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityActionService {

    private final SecurityActionRepository repository;

    public SecurityActionService(SecurityActionRepository repository) {
        this.repository = repository;
    }

    public List<SecurityAction> getAllActions() {
        return repository.findAll();
    }

    public SecurityAction getActionById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Security action not found: " + id));
    }
}