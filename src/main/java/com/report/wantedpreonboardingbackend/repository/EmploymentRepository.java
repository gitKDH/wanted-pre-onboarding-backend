package com.report.wantedpreonboardingbackend.repository;

import com.report.wantedpreonboardingbackend.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentRepository extends JpaRepository<Employment,Long> {
}
