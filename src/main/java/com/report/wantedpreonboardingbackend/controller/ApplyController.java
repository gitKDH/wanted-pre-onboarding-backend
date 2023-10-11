package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.entity.Apply;
import com.report.wantedpreonboardingbackend.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @PostMapping("/apply")
    public ResponseEntity<Apply> applyForJob(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long employmentId = request.get("employmentId");
        Apply apply = applyService.applyForJob(userId, employmentId);
        return ResponseEntity.ok(apply);
    }
}
