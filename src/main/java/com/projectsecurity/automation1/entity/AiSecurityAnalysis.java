package com.projectsecurity.automation1.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_security_analysis")
public class AiSecurityAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "incident_id")
    private Integer incidentId;

    @Column(name = "attack_type")
    private String attackType;

    @Column(name = "risk_score")
    private Integer riskScore;

    @Column(name = "severity")
    private String severity;

    @Column(name = "confidence")
    private Integer confidence;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "recommended_action")
    private String recommendedAction;

    @Column(name = "analyzed_at")
    private LocalDateTime analyzedAt;

    public AiSecurityAnalysis() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Integer incidentId) {
        this.incidentId = incidentId;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}