package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}