package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }
}
