package com.projectsecurity.automation1.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_events")
public class SecurityEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @Column(name = "metadata", columnDefinition = "json")
    private String metadata;

    @Column(name = "old_role")
    private String oldRole;

    @Column(name = "new_role")
    private String newRole;

    public SecurityEvent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getOldRole() {
        return oldRole;
    }

    public void setOldRole(String oldRole) {
        this.oldRole = oldRole;
    }

    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}