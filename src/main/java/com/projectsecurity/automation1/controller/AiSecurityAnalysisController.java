package com.projectsecurity.automation1.controller;

import com.projectsecurity.automation1.entity.AiSecurityAnalysis;
import com.projectsecurity.automation1.service.AiSecurityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-analysis")
@CrossOrigin(origins = "*")
public class AiSecurityAnalysisController {

    private final AiSecurityAnalysisService service;

    public AiSecurityAnalysisController(AiSecurityAnalysisService service) {
        this.service = service;
    }

    @GetMapping
    public List<AiSecurityAnalysis> getAllAnalyses() {
        return service.getAllAnalyses();
    }

    @GetMapping("/{id}")
    public AiSecurityAnalysis getAnalysisById(@PathVariable Integer id) {
        return service.getAnalysisById(id);
    }
}