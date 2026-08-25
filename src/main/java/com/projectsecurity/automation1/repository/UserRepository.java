package com.projectsecurity.automation1.repository;

import com.projectsecurity.automation1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("""
    SELECT COUNT(u)
    FROM User u
    WHERE u.accountStatus = 'DISABLED'
    """)
    long countBlockedUsers();
}