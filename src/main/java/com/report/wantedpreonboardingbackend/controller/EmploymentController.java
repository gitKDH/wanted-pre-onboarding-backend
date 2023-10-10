package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employments")
public class EmploymentController {

    @Autowired
    private EmploymentService employmentService;

    @PostMapping("/")
    public Employment createEmployment(@RequestBody Employment employment) {
        return employmentService.createEmployment(employment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employment> updateEmployment(@PathVariable Long id, @RequestBody Employment employmentDetails) {
        Employment employment = employmentService.updateEmployment(id, employmentDetails);
        return new ResponseEntity<>(employment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployment(@PathVariable Long id) {
        employmentService.deleteEmployment(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "채용공고 " + id + "번" + " 삭제 완료.");

        return ResponseEntity.ok(response);
    }

}
