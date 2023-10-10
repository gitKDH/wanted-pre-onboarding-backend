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
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회사 아이디가 없습니다."));

        employment.setCompany(company);

        return employmentRepository.save(employment);
    }

    public Employment updateEmployment(Long id, Employment employmentDetails) {
        Employment employment = employmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 채용 공고가 없습니다." + id));

        employment.setPosition(employmentDetails.getPosition());
        employment.setReward(employmentDetails.getReward());
        employment.setDetail(employmentDetails.getDetail());
        employment.setSkill(employmentDetails.getSkill());

        return employmentRepository.save(employment);
    }

    public void deleteEmployment(Long id) {
        employmentRepository.deleteById(id);
    }
}
