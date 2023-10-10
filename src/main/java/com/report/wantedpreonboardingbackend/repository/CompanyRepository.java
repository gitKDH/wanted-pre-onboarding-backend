package com.report.wantedpreonboardingbackend.repository;

import com.report.wantedpreonboardingbackend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
