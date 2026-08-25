package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.SecurityIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecurityIncidentRepository
        extends JpaRepository<SecurityIncident, Integer> {

    @Query("""
        SELECT s.severity, COUNT(s)
        FROM SecurityIncident s
        GROUP BY s.severity
        """)
    List<Object[]> countBySeverity();

    @Query("""
        SELECT s.incidentType, COUNT(s)
        FROM SecurityIncident s
        GROUP BY s.incidentType
        ORDER BY COUNT(s) DESC
        """)
    List<Object[]> countByIncidentType();

    @Query("""
        SELECT s.responseStatus, COUNT(s)
        FROM SecurityIncident s
        GROUP BY s.responseStatus
        """)
    List<Object[]> countByResponseStatus();

    @Query("""
        SELECT s.userId, COUNT(s)
        FROM SecurityIncident s
        GROUP BY s.userId
        ORDER BY COUNT(s) DESC
        """)
    List<Object[]> countByUser();

    @Query("""
    SELECT COUNT(s)
    FROM SecurityIncident s
    WHERE s.severity = 'CRITICAL'
    """)
    long countCriticalIncidents();

    @Query("""
    SELECT COUNT(s)
    FROM SecurityIncident s
    WHERE s.riskScore >= 70
    """)
    long countHighRiskIncidents();

    @Query("""
    SELECT COALESCE(AVG(s.riskScore), 0)
    FROM SecurityIncident s
    """)
    Double getAverageRiskScore();

    @Query("""
    SELECT FUNCTION('DATE', s.eventTime), COUNT(s)
    FROM SecurityIncident s
    GROUP BY FUNCTION('DATE', s.eventTime)
    ORDER BY FUNCTION('DATE', s.eventTime)
    """)
    List<Object[]> countIncidentsByDate();

    @Query("""
    SELECT s.riskScore, COUNT(s)
    FROM SecurityIncident s
    GROUP BY s.riskScore
    ORDER BY s.riskScore
    """)
    List<Object[]> countByRiskScore();

    @Query("""
    SELECT
        FUNCTION('DAYOFWEEK', s.eventTime),
        FUNCTION('HOUR', s.eventTime),
        COUNT(s)
    FROM SecurityIncident s
    GROUP BY
        FUNCTION('DAYOFWEEK', s.eventTime),
        FUNCTION('HOUR', s.eventTime)
    ORDER BY
        FUNCTION('DAYOFWEEK', s.eventTime),
        FUNCTION('HOUR', s.eventTime)
    """)
    List<Object[]> countIncidentsByDayAndHour();
}