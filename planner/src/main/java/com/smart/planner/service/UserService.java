package com.smart.planner.service;

import com.smart.planner.model.User;
import com.smart.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // BCrypt ka object banaya password ko encrypt karne ke liye
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 1. Naya User Register Karne Ke Liye (With Password Encryption)
    public User registerUser(User user) {
        // Check karenge ki email pehle se register toh nahi hai
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        
        //  Industry Standard: Plain password ko encrypt (hash) karke set kar rahe hain
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        
        return userRepository.save(user);
    }

    // 2. Email Se User Dhoodhne Ke Liye 
    public Optional<User> getUserByEmail(String email) {
        // Iska use hum UserController mein login password match karne ke liye karenge
        return userRepository.findByEmail(email);
    }

    // 3. ID Se User Dhoodhne Ke Liye
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    //  Extra Helper: Controller ke liye plain password aur encrypted password match karne ke liye
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}