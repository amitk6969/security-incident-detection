package com.projectsecurity.automation1.dto;

public class DashboardStatistic {

    private String label;
    private long count;

    public DashboardStatistic() {
    }

    public DashboardStatistic(String label, long count) {
        this.label = label;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}