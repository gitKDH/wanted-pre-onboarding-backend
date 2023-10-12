package com.report.wantedpreonboardingbackend;
import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.dto.EmploymentDTO;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentUpdateUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateEmployment() {
        // 샘플 EmploymentDTO 데이터 생성
        EmploymentDTO employmentDTO = new EmploymentDTO(1L, "Updated Position", 60000, "Updated Detail", "Updated Skill");

        // 샘플 Employment 데이터 생성
        Employment existingEmployment = new Employment("Sample Position", 50000, "Sample Detail", "Sample Skill", null);
        existingEmployment.setId(1L);

        // employmentService.findEmploymentById가 호출될 때, 샘플 Employment을 리턴하도록 설정
        Mockito.when(employmentService.findEmploymentById(1L)).thenReturn(existingEmployment);

        // EmploymentController를 통해 채용 정보 업데이트
        ResponseEntity<Map<String, Object>> response = employmentController.updateEmployment(1L, employmentDTO);

        // HTTP 응답 코드 확인 (예: 200 - OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // 업데이트된 채용 정보 객체를 가져오고 필드 값 확인
        Map<String, Object> updatedEmploymentResponse = response.getBody();
        assertNotNull(updatedEmploymentResponse);
        assertEquals("Updated Position", updatedEmploymentResponse.get("position"));
        assertEquals(60000, updatedEmploymentResponse.get("reward"));
        assertEquals("Updated Detail", updatedEmploymentResponse.get("detail"));
        assertEquals("Updated Skill", updatedEmploymentResponse.get("skill"));
    }
}