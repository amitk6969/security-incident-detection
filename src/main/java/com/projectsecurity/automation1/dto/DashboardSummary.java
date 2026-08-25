package com.projectsecurity.automation1.dto;

public class DashboardSummary {

    private long totalIncidents;
    private long criticalIncidents;
    private long highRiskIncidents;
    private long blockedUsers;
    private double averageRiskScore;

    public DashboardSummary() {
    }

    public DashboardSummary(
            long totalIncidents,
            long criticalIncidents,
            long highRiskIncidents,
            long blockedUsers,
            double averageRiskScore) {

        this.totalIncidents = totalIncidents;
        this.criticalIncidents = criticalIncidents;
        this.highRiskIncidents = highRiskIncidents;
        this.blockedUsers = blockedUsers;
        this.averageRiskScore = averageRiskScore;
    }

    public long getTotalIncidents() {
        return totalIncidents;
    }

    public void setTotalIncidents(long totalIncidents) {
        this.totalIncidents = totalIncidents;
    }

    public long getCriticalIncidents() {
        return criticalIncidents;
    }

    public void setCriticalIncidents(long criticalIncidents) {
        this.criticalIncidents = criticalIncidents;
    }

    public long getHighRiskIncidents() {
        return highRiskIncidents;
    }

    public void setHighRiskIncidents(long highRiskIncidents) {
        this.highRiskIncidents = highRiskIncidents;
    }

    public long getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(long blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public double getAverageRiskScore() {
        return averageRiskScore;
    }

    public void setAverageRiskScore(double averageRiskScore) {
        this.averageRiskScore = averageRiskScore;
    }
}