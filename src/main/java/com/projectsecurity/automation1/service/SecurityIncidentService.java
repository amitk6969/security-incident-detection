package com.projectsecurity.automation1.service;

import com.projectsecurity.automation1.entity.SecurityIncident;
import com.projectsecurity.automation1.repository.SecurityIncidentRepository;
import org.springframework.stereotype.Service;
import com.projectsecurity.automation1.dto.DashboardStatistic;
import com.projectsecurity.automation1.dto.DashboardSummary;
import com.projectsecurity.automation1.repository.UserRepository;
import java.util.Map;
import java.util.HashMap;

import java.util.stream.Collectors;

import java.util.List;

@Service
public class SecurityIncidentService {
    private final UserRepository userRepository;
    private final SecurityIncidentRepository repository;

    public SecurityIncidentService(
            SecurityIncidentRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<SecurityIncident> getAllIncidents() {
        return repository.findAll();
    }

    public SecurityIncident getIncidentById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Incident not found with id: " + id));
    }

    public long getIncidentCount() {
        return repository.count();
    }
    public List<DashboardStatistic> getSeverityStatistics() {

        return repository.countBySeverity()
                .stream()
                .map(row -> new DashboardStatistic(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
    public List<DashboardStatistic> getIncidentTypeStatistics() {

        return repository.countByIncidentType()
                .stream()
                .map(row -> new DashboardStatistic(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
    public List<DashboardStatistic> getResponseStatusStatistics() {

        return repository.countByResponseStatus()
                .stream()
                .map(row -> new DashboardStatistic(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
    public List<DashboardStatistic> getUserStatistics() {

        return repository.countByUser()
                .stream()
                .map(row -> new DashboardStatistic(
                        (String) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
    public List<DashboardStatistic> getIncidentTimeline() {

        return repository.countIncidentsByDate()
                .stream()
                .map(row -> new DashboardStatistic(
                        row[0].toString(),
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
    public List<DashboardStatistic> getRiskScoreStatistics() {

        return repository.countByRiskScore()
                .stream()
                .map(row -> new DashboardStatistic(
                        row[0].toString(),
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAttackHeatmap() {

        return repository.countIncidentsByDayAndHour()
                .stream()
                .map(row -> {
                    Map<String, Object> data = new HashMap<>();

                    data.put("day", ((Number) row[0]).intValue());
                    data.put("hour", ((Number) row[1]).intValue());
                    data.put("count", ((Number) row[2]).longValue());

                    return data;
                })
                .collect(Collectors.toList());
    }
    public DashboardSummary getDashboardSummary() {

        long totalIncidents = repository.count();

        long criticalIncidents =
                repository.countCriticalIncidents();

        long highRiskIncidents =
                repository.countHighRiskIncidents();

        long blockedUsers =
                userRepository.countBlockedUsers();

        Double averageRiskScore =
                repository.getAverageRiskScore();

        return new DashboardSummary(
                totalIncidents,
                criticalIncidents,
                highRiskIncidents,
                blockedUsers,
                averageRiskScore
        );
    }
}