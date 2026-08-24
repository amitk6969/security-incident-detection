package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.SecurityIncident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityIncidentRepository extends JpaRepository<SecurityIncident, Integer> {
}