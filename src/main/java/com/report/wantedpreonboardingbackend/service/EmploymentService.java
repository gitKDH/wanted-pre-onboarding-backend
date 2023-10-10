package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.repository.CompanyRepository;
import com.report.wantedpreonboardingbackend.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Map<String, Object>> getAllEmployments() {
        List<Employment> employments = employmentRepository.findAll();
        return convertToResponseList(employments);
    }

    public List<Map<String, Object>> searchEmployments(String keyword) {
        List<Employment> employments = employmentRepository.findByCompanyNameContainingOrCompanyNationContainingOrPositionContainingOrSkillContaining(keyword, keyword, keyword, keyword);
        return convertToResponseList(employments);
    }

    private List<Map<String, Object>> convertToResponseList(List<Employment> employments) {
        return employments.stream()
                .map(emp -> {
                    Map<String, Object> response = new LinkedHashMap<>();
                    response.put("id", emp.getId());
                    response.put("companyName", emp.getCompany().getName());
                    response.put("nation", emp.getCompany().getNation());
                    response.put("region", emp.getCompany().getRegion());
                    response.put("position", emp.getPosition());
                    response.put("reward", emp.getReward());
                    response.put("skill", emp.getSkill());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
