package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.AiSecurityAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiSecurityAnalysisRepository extends JpaRepository<AiSecurityAnalysis, Integer> {
}