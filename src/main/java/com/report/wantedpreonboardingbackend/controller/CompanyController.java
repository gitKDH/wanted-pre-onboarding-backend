package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.dto.CompanyDTO;
import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        CompanyDTO returnCompany = new CompanyDTO();
        returnCompany.setId(createdCompany.getId());
        returnCompany.setName(createdCompany.getName());
        returnCompany.setNation(createdCompany.getNation());
        returnCompany.setRegion(createdCompany.getRegion());
        return new ResponseEntity<>(returnCompany, HttpStatus.CREATED);
    }
}
