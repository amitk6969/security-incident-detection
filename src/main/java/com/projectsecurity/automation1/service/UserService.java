package com.projectsecurity.automation1.service;

import com.projectsecurity.automation1.entity.User;
import com.projectsecurity.automation1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(String userId) {
        return repository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId));
    }

    public long getUserCount() {
        return repository.count();
    }
}