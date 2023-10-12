package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.CompanyController;
import com.report.wantedpreonboardingbackend.dto.CompanyDTO;
import com.report.wantedpreonboardingbackend.entity.Company;
import com.report.wantedpreonboardingbackend.service.CompanyService;
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

public class CompanyCreateUnitTest {
    @InjectMocks
    private CompanyController companyController;

    @Mock
    private CompanyService companyService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCompany() {
        Company company = new Company();
        company.setName("원티드");
        company.setNation("korea");
        company.setRegion("ansan");

        Mockito.when(companyService.createCompany(company)).thenReturn(company);

        ResponseEntity<CompanyDTO> response = companyController.createCompany(company);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        CompanyDTO createdCompany = response.getBody();
        assertNotNull(createdCompany);
        assertEquals("원티드", createdCompany.getName());
        assertEquals("korea", createdCompany.getNation());
        assertEquals("ansan", createdCompany.getRegion());
    }
}
