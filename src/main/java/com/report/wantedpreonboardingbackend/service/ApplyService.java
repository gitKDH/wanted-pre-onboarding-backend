package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.dto.ApplyDTO;
import com.report.wantedpreonboardingbackend.entity.Apply;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.exceptions.DuplicateApplicationException;
import com.report.wantedpreonboardingbackend.repository.ApplyRepository;
import com.report.wantedpreonboardingbackend.repository.UserRepository;
import com.report.wantedpreonboardingbackend.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmploymentRepository employmentRepository;

    public Apply applyForJob(ApplyDTO applyDTO) {
        User user = userRepository.findById(applyDTO.getUserId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Employment employment = employmentRepository.findById(applyDTO.getEmploymentId()).orElseThrow(() -> new RuntimeException("공고를 찾을 수 없습니다."));

        if (applyRepository.findByUserAndEmployment(user, employment).isPresent()) {
            throw new DuplicateApplicationException("이미 지원한 공고입니다.");
        }

        Apply apply = new Apply(user, employment);
        return applyRepository.save(apply);
    }
}
