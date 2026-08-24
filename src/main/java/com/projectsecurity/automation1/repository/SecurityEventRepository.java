package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.SecurityEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityEventRepository extends JpaRepository<SecurityEvent, Integer> {
}