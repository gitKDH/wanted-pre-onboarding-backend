package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.dto.ApplyDTO;
import com.report.wantedpreonboardingbackend.entity.Apply;
import com.report.wantedpreonboardingbackend.entity.Employment;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.repository.ApplyRepository;
import com.report.wantedpreonboardingbackend.repository.EmploymentRepository;
import com.report.wantedpreonboardingbackend.repository.UserRepository;
import com.report.wantedpreonboardingbackend.service.ApplyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ApplyForJobUnitTest {
    @InjectMocks
    private ApplyService applyService;

    @Mock
    private ApplyRepository applyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmploymentRepository employmentRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApplyForJob() {
        User user = new User();
        user.setId(1L);

        Employment employment = new Employment();
        employment.setId(1L);

        ApplyDTO applyDTO = new ApplyDTO();
        applyDTO.setUserId(1L);
        applyDTO.setEmploymentId(1L);

        when(applyRepository.findByUserAndEmployment(user, employment)).thenReturn(Optional.empty());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(employmentRepository.findById(1L)).thenReturn(Optional.of(employment));

        when(applyRepository.save(any(Apply.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Apply apply = applyService.applyForJob(applyDTO);
        assert apply != null;

        when(applyRepository.findByUserAndEmployment(user, employment)).thenReturn(Optional.of(new Apply(user, employment)));

        assertThrows(RuntimeException.class, () -> applyService.applyForJob(applyDTO));
    }
}
