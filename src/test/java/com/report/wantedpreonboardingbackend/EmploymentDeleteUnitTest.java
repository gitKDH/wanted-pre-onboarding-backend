package com.report.wantedpreonboardingbackend;
import com.report.wantedpreonboardingbackend.controller.EmploymentController;
import com.report.wantedpreonboardingbackend.service.EmploymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentDeleteUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteEmployment() {
        Long employmentId = 1L;

        Mockito.doNothing().when(employmentService).deleteEmployment(employmentId);

        ResponseEntity<?> response = employmentController.deleteEmployment(employmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals("채용공고 1번 삭제 완료.", ((Map<String, String>) response.getBody()).get("message"));
    }
}