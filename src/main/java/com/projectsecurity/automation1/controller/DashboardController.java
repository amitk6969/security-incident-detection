package com.projectsecurity.automation1.controller;

import com.projectsecurity.automation1.dto.DashboardStatistic;
import com.projectsecurity.automation1.service.SecurityIncidentService;
import org.springframework.web.bind.annotation.*;
import com.projectsecurity.automation1.dto.DashboardSummary;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final SecurityIncidentService incidentService;

    public DashboardController(SecurityIncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/severity")
    public List<DashboardStatistic> getSeverityStatistics() {
        return incidentService.getSeverityStatistics();
    }

    @GetMapping("/incident-types")
    public List<DashboardStatistic> getIncidentTypeStatistics() {
        return incidentService.getIncidentTypeStatistics();
    }

    @GetMapping("/response-status")
    public List<DashboardStatistic> getResponseStatusStatistics() {
        return incidentService.getResponseStatusStatistics();
    }

    @GetMapping("/users")
    public List<DashboardStatistic> getUserStatistics() {
        return incidentService.getUserStatistics();
    }
    @GetMapping("/summary")
    public DashboardSummary getDashboardSummary() {
        return incidentService.getDashboardSummary();
    }
    @GetMapping("/timeline")
    public List<DashboardStatistic> getIncidentTimeline() {
        return incidentService.getIncidentTimeline();
    }
    @GetMapping("/risk-distribution")
    public List<DashboardStatistic> getRiskScoreStatistics() {
        return incidentService.getRiskScoreStatistics();
    }
    @GetMapping("/attack-heatmap")
    public List<Map<String, Object>> getAttackHeatmap() {
        return incidentService.getAttackHeatmap();
    }
}