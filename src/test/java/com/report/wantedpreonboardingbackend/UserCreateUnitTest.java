package com.report.wantedpreonboardingbackend;

import com.report.wantedpreonboardingbackend.controller.UserController;
import com.report.wantedpreonboardingbackend.dto.UserDTO;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.service.UserService;
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

public class UserCreateUnitTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("김자바");

        Mockito.when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<UserDTO> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        UserDTO createdUser = response.getBody();
        assertNotNull(createdUser);
        assertEquals("김자바", createdUser.getName());
    }
}
