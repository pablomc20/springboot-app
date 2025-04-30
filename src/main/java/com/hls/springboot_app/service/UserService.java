package com.hls.springboot_app.service;

import com.hls.springboot_app.model.User;
import com.hls.springboot_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
