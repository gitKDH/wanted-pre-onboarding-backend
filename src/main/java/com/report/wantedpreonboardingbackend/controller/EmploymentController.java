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
    public ResponseEntity<EmploymentDTO> createEmployment(@RequestBody EmploymentDTO employmentDTO) {
        EmploymentDTO returnEmploymentDTO = employmentService.createEmployment(employmentDTO);
        return new ResponseEntity<> (returnEmploymentDTO, HttpStatus.CREATED);
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

        employmentService.updateEmployment(existingEmployment);

        // 필요한 필드를 반환하도록 수정
        Map<String, Object> updatedEmploymentResponse = new LinkedHashMap<>();
        updatedEmploymentResponse.put("position", existingEmployment.getPosition());
        updatedEmploymentResponse.put("reward", existingEmployment.getReward());
        updatedEmploymentResponse.put("detail", existingEmployment.getDetail());
        updatedEmploymentResponse.put("skill", existingEmployment.getSkill());

        return new ResponseEntity<>(updatedEmploymentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployment(@PathVariable Long id) {
        employmentService.deleteEmployment(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "채용공고 " + id + "번" + " 삭제 완료.");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEmployments() {
        List<Map<String, Object>> employments = employmentService.getAllEmployments();
        return new ResponseEntity<>(employments, HttpStatus.OK);
    }
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
