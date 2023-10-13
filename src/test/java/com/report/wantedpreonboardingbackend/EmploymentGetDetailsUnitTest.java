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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmploymentGetDetailsUnitTest {
    @InjectMocks
    private EmploymentController employmentController;

    @Mock
    private EmploymentService employmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEmploymentDetailsWithOtherOpenings() {
        Long employmentId = 1L;

        Map<String, Object> employmentDetails = new HashMap<>();
        employmentDetails.put("채용공고_id", employmentId);
        employmentDetails.put("회사명", "원티드랩");
        employmentDetails.put("국가", "한국");
        employmentDetails.put("지역", "서울");
        employmentDetails.put("채용포지션", "백엔드 주니어 개발자");
        employmentDetails.put("채용보상금", 1500000);
        employmentDetails.put("사용기술", "Python");
        employmentDetails.put("채용내용", "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..");

        List<Long> otherOpenings = new ArrayList<>();
        otherOpenings.add(2L);
        otherOpenings.add(3L);
        employmentDetails.put("회사가올린다른채용공고", otherOpenings);

        Mockito.when(employmentService.getEmploymentDetails(employmentId)).thenReturn(employmentDetails);

        ResponseEntity<Map<String, Object>> response = employmentController.getEmploymentDetails(employmentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> returnedEmploymentDetails = response.getBody();
        assertNotNull(returnedEmploymentDetails);

        assertEquals(employmentId, returnedEmploymentDetails.get("채용공고_id"));
        assertEquals("원티드랩", returnedEmploymentDetails.get("회사명"));
        assertEquals("한국", returnedEmploymentDetails.get("국가"));
        assertEquals("서울", returnedEmploymentDetails.get("지역"));
        assertEquals("백엔드 주니어 개발자", returnedEmploymentDetails.get("채용포지션"));
        assertEquals(1500000, returnedEmploymentDetails.get("채용보상금"));
        assertEquals("Python", returnedEmploymentDetails.get("사용기술"));
        assertEquals("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", returnedEmploymentDetails.get("채용내용"));

        List<Long> returnedOtherOpenings = (List<Long>) returnedEmploymentDetails.get("회사가올린다른채용공고");
        assertNotNull(returnedOtherOpenings);
        assertEquals(2, returnedOtherOpenings.size());
        assertEquals(2L, (long) returnedOtherOpenings.get(0));
        assertEquals(3L, (long) returnedOtherOpenings.get(1));
    }
}