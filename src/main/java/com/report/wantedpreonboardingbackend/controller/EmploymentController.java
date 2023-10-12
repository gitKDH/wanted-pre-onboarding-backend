package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.dto.EmploymentDTO;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employment")
public class EmploymentController {

    @Autowired
    private final EmploymentService employmentService;

    public EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @PostMapping
    public EmploymentDTO createEmployment(@RequestBody EmploymentDTO employmentDTO) {
        return employmentService.createEmployment(employmentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployment(@PathVariable Long id, @RequestBody EmploymentDTO employmentDTO) {
        Employment existingEmployment = employmentService.findEmploymentById(id);
        if (existingEmployment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingEmployment.setPosition(employmentDTO.getPosition());
        existingEmployment.setReward(employmentDTO.getReward());
        existingEmployment.setDetail(employmentDTO.getDetail());
        existingEmployment.setSkill(employmentDTO.getSkill());

        Map<String, Object> updatedEmployment = employmentService.updateEmployment(existingEmployment);

        Map<String, Object> responseDTO = new LinkedHashMap<>();
        responseDTO.put("position", updatedEmployment.get("position"));
        responseDTO.put("reward", updatedEmployment.get("reward"));
        responseDTO.put("detail", updatedEmployment.get("detail"));
        responseDTO.put("skill", updatedEmployment.get("skill"));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployment(@PathVariable Long id) {
        employmentService.deleteEmployment(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "채용공고 " + id + "번" + " 삭제 완료.");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEmployments(@RequestParam(required = false) String search) {
        if (search != null) {
            List<Map<String, Object>> employments = employmentService.searchEmployments(search);
            return new ResponseEntity<>(employments, HttpStatus.OK);
        }
        List<Map<String, Object>> employments = employmentService.getAllEmployments();
        return new ResponseEntity<>(employments, HttpStatus.OK);
    }

    @GetMapping("/{employmentId}")
    public ResponseEntity<Map<String, Object>> getEmploymentDetails(@PathVariable Long employmentId) {
        Map<String, Object> employmentDetails = employmentService.getEmploymentDetails(employmentId);
        return new ResponseEntity<>(employmentDetails, HttpStatus.OK);
    }

}
