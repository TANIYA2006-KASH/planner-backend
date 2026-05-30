package com.smart.planner.service;

import com.smart.planner.model.User;
import com.smart.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 1. Naya User Register Karne Ke Liye
    public User registerUser(User user) {
        // Check karenge ki email pehle se register toh nahi hai
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        // Abhi hum password direct save kar rahe hain 
        return userRepository.save(user);
    }

    // 2. Email Se User Dhoodhne Ke Liye 
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 3. ID Se User Dhoodhne Ke Liye
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
