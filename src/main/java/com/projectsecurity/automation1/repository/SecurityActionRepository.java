package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.SecurityAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityActionRepository extends JpaRepository<SecurityAction, Integer> {
}