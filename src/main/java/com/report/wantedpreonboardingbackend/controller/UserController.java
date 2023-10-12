package com.report.wantedpreonboardingbackend.controller;

import com.report.wantedpreonboardingbackend.dto.UserDTO;
import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        UserDTO returnUser = new UserDTO();
        returnUser.setName((createdUser.getName()));
        return new ResponseEntity<>(returnUser, HttpStatus.CREATED);
    }
}

