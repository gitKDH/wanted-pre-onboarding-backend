package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.dto.ApplyDTO;
import com.report.wantedpreonboardingbackend.entity.Apply;
import com.report.wantedpreonboardingbackend.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @PostMapping
    public ResponseEntity<ApplyDTO> applyForJob(@RequestBody ApplyDTO applyDTO) {
        Apply apply = applyService.applyForJob(applyDTO);
        ApplyDTO returnApply = new ApplyDTO();
        returnApply.setUserId(apply.getUser().getId());
        returnApply.setEmploymentId(apply.getEmployment().getId());
        return new ResponseEntity<>(returnApply, HttpStatus.CREATED);
    }
}
