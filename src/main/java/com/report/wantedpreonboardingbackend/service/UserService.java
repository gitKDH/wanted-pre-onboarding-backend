package com.report.wantedpreonboardingbackend.service;

import com.report.wantedpreonboardingbackend.entity.User;
import com.report.wantedpreonboardingbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}

