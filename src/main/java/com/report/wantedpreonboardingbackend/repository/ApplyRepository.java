package com.report.wantedpreonboardingbackend.repository;

import com.report.wantedpreonboardingbackend.entity.Apply;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findByUserAndEmployment(User user, Employment employment);
}

