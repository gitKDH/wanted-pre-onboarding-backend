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

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentGetAllUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployments() {
        Map<String, Object> employment1  = new HashMap<>();
        employment1.put("position", "Position 1");
        employment1.put("reward",1000);
        employment1.put("detail","Detail 1");
        employment1.put("skill","Skill 1");
        Map<String, Object> employment2  = new HashMap<>();
        employment2.put("position", "Position 2");
        employment2.put("reward",2000);
        employment2.put("detail","Detail 2");
        employment2.put("skill","Skill 2");
        List<Map<String, Object>> employmentList = new ArrayList<>();

        employmentList.add(employment1);
        employmentList.add(employment2);

        Mockito.when(employmentService.getAllEmployments()).thenReturn(employmentList);

        ResponseEntity<List<Map<String, Object>>> response = employmentController.getAllEmployments();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Map<String, Object>> returnedEmployments = response.getBody();
        assertNotNull(returnedEmployments);
        assertEquals(2, returnedEmployments.size());

        Map<String, Object> returnedEmployment1 = returnedEmployments.get(0);
        assertEquals("Position 1", returnedEmployment1.get("position"));
        assertEquals(Integer.valueOf(1000), returnedEmployment1.get("reward"));
        assertEquals("Detail 1", returnedEmployment1.get("detail"));
        assertEquals("Skill 1", returnedEmployment1.get("skill"));

        Map<String, Object> returnedEmployment2 = returnedEmployments.get(1);
        assertEquals("Position 2", returnedEmployment2.get("position"));
        assertEquals(Integer.valueOf(2000), returnedEmployment2.get("reward"));
        assertEquals("Detail 2", returnedEmployment2.get("detail"));
        assertEquals("Skill 2", returnedEmployment2.get("skill"));
    }
}
