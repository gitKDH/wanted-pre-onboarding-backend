package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class EmploymentController {

    @Autowired
    private EmploymentService employmentService;

    @PostMapping("/employments")
    public Employment createEmployment(@RequestBody Employment employment) {
        return employmentService.createEmployment(employment);
    }
    @PutMapping("/employments/{id}")
    public ResponseEntity<Employment> updateEmployment(@PathVariable Long id, @RequestBody Employment employmentDetails) {
        Employment employment = employmentService.updateEmployment(id, employmentDetails);
        return new ResponseEntity<>(employment, HttpStatus.OK);
    }

}
