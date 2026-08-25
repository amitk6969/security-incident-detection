package com.projectsecurity.automation1.controller;

import com.projectsecurity.automation1.entity.SecurityAction;
import com.projectsecurity.automation1.service.SecurityActionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
@CrossOrigin(origins = "*")
public class SecurityActionController {

    private final SecurityActionService service;

    public SecurityActionController(SecurityActionService service) {
        this.service = service;
    }

    @GetMapping
    public List<SecurityAction> getAllActions() {
        return service.getAllActions();
    }

    @GetMapping("/{id}")
    public SecurityAction getActionById(@PathVariable Integer id) {
        return service.getActionById(id);
    }
}