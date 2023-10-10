package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.repository.CompanyRepository;
import com.report.wantedpreonboardingbackend.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentService {

    @Autowired
    private EmploymentRepository employmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Employment createEmployment(Employment employment) {
        Company company = companyRepository.findById(employment.getCompany().getId())
                .orElseThrow(() -> new IllegalArgumentException("Company ID not found!"));

        employment.setCompany(company);

        return employmentRepository.save(employment);
    }
}
