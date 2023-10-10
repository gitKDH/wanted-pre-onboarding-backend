package com.report.wantedpreonboardingbackend.repository;

import com.report.wantedpreonboardingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
