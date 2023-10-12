package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.dto.EmploymentDTO;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentCreateUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployment() {
        EmploymentDTO employmentDTO = new EmploymentDTO(1L, "백엔드", 50000, "백엔드 개발자를 채용합니다.", "java");

        Mockito.when(employmentService.createEmployment(employmentDTO)).thenReturn(employmentDTO);

        ResponseEntity<EmploymentDTO> response = employmentController.createEmployment(employmentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        EmploymentDTO createdEmployment = response.getBody();
        assertNotNull(createdEmployment);
        assertEquals(1L, createdEmployment.getCompany_id().longValue());
        assertEquals("백엔드", createdEmployment.getPosition());
        assertEquals(50000, createdEmployment.getReward());
        assertEquals("백엔드 개발자를 채용합니다.", createdEmployment.getDetail());
        assertEquals("java", createdEmployment.getSkill());
    }
}