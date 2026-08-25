package com.projectsecurity.automation1.service;

import com.projectsecurity.automation1.entity.AiSecurityAnalysis;
import com.projectsecurity.automation1.repository.AiSecurityAnalysisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiSecurityAnalysisService {

    private final AiSecurityAnalysisRepository repository;

    public AiSecurityAnalysisService(AiSecurityAnalysisRepository repository) {
        this.repository = repository;
    }

    public List<AiSecurityAnalysis> getAllAnalyses() {
        return repository.findAll();
    }

    public AiSecurityAnalysis getAnalysisById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("AI analysis not found: " + id));
    }
}