package com.projectsecurity.automation1.controller;

import com.projectsecurity.automation1.entity.SecurityIncident;
import com.projectsecurity.automation1.service.SecurityIncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "*")
public class SecurityIncidentController {

    private final SecurityIncidentService service;

    public SecurityIncidentController(SecurityIncidentService service) {
        this.service = service;
    }

    @GetMapping
    public List<SecurityIncident> getAllIncidents() {
        return service.getAllIncidents();
    }
    @GetMapping("/count")
    public long getIncidentCount() {
        return service.getIncidentCount();
    }
    @GetMapping("/{id}")
    public SecurityIncident getIncidentById(@PathVariable Integer id) {
        return service.getIncidentById(id);
    }


}