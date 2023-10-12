package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.dto.CompanyDTO;
import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);

    }
}
