package com.projectsecurity.automation1.controller;

import com.projectsecurity.automation1.entity.User;
import com.projectsecurity.automation1.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return service.getUserById(userId);
    }

    @GetMapping("/count")
    public long getUserCount() {
        return service.getUserCount();
    }

}