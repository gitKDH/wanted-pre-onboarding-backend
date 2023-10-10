package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmploymentController {

    @Autowired
    private EmploymentService employmentService;

    @PostMapping("/employments")
    public Employment createEmployment(@RequestBody Employment employment) {
        return employmentService.createEmployment(employment);
    }
}
