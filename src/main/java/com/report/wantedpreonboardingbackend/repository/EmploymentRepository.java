package com.report.wantedpreonboardingbackend.repository;

import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentRepository extends JpaRepository<Employment,Long> {
    List<Employment> findByCompanyNameContainingOrCompanyNationContainingOrPositionContainingOrSkillContaining(String keyword, String keyword2, String keyword3, String keyword4);

    List<Employment> findByCompany(Company company);
}
