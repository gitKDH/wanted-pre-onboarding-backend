package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
}

