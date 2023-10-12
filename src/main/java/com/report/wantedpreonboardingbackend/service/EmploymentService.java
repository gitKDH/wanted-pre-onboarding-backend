package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.dto.EmploymentDTO;
import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.repository.CompanyRepository;
import com.report.wantedpreonboardingbackend.repository.EmploymentRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final CompanyRepository companyRepository;

    public EmploymentService(EmploymentRepository employmentRepository, CompanyRepository companyRepository) {
        this.employmentRepository = employmentRepository;
        this.companyRepository = companyRepository;
    }

    public EmploymentDTO createEmployment(EmploymentDTO employmentDTO) {
        Company company = companyRepository.findById(employmentDTO.getCompany_id())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회사 아이디가 없습니다."));

        Employment employment = new Employment();
        employment.setCompany(company);
        employment.setPosition(employmentDTO.getPosition());
        employment.setReward(employmentDTO.getReward());
        employment.setDetail(employmentDTO.getDetail());
        employment.setSkill(employmentDTO.getSkill());

        Employment savedEmployment = employmentRepository.save(employment);

        return new EmploymentDTO(
                savedEmployment.getCompany().getId(),
                savedEmployment.getPosition(),
                savedEmployment.getReward(),
                savedEmployment.getDetail(),
                savedEmployment.getSkill()
        );
    }

    public Employment findEmploymentById(Long id) {
        return employmentRepository.findById(id).orElse(null);
    }

    public Map<String, Object> updateEmployment(Employment employment) {
        employmentRepository.save(employment);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("position", employment.getPosition());
        response.put("reward", employment.getReward());
        response.put("skill", employment.getSkill());
        response.put("detail", employment.getDetail());
        return response;
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

    public Map<String, Object> getEmploymentDetails(Long employmentId) {
        Employment employment = employmentRepository.findById(employmentId).orElse(null);
        if (employment == null) {
            return Collections.singletonMap("오류", "채용공고가 없습니다.");
        }

        List<Long> otherEmploymentIds = employmentRepository.findByCompany(employment.getCompany())
                .stream()
                .filter(emp -> !emp.getId().equals(employmentId))
                .map(Employment::getId)
                .collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", employment.getId());
        response.put("companyName", employment.getCompany().getName());
        response.put("nation", employment.getCompany().getNation());
        response.put("region", employment.getCompany().getRegion());
        response.put("position", employment.getPosition());
        response.put("reward", employment.getReward());
        response.put("skill", employment.getSkill());
        response.put("detail", employment.getDetail());
        response.put("otherEmployment", otherEmploymentIds);

        return response;
    }
}
