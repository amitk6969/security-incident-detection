package com.projectsecurity.automation1.controller;

import com.projectsecurity.automation1.entity.SecurityEvent;
import com.projectsecurity.automation1.service.SecurityEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class SecurityEventController {

    private final SecurityEventService service;

    public SecurityEventController(SecurityEventService service) {
        this.service = service;
    }

    @GetMapping
    public List<SecurityEvent> getAllEvents() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public SecurityEvent getEventById(@PathVariable Integer id) {
        return service.getEventById(id);
    }

    @GetMapping("/count")
    public long getEventCount() {
        return service.getEventCount();
    }
}