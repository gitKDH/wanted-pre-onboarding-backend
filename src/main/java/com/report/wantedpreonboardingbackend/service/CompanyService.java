package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
}
