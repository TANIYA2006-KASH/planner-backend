package com.smart.planner.controller;

import com.smart.planner.model.User;
import com.smart.planner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Taaki frontend aaram se bina CORS error ke connect ho sake
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Endpoint: User Register Karne Ke Liye 
    // URL: http://localhost:8080/api/users/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2. Endpoint: User Login Karne Ke Liye
    // URL: http://localhost:8080/api/users/login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        Optional<User> userOpt = userService.getUserByEmail(loginDetails.getEmail());
        
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginDetails.getPassword())) {
            return ResponseEntity.ok(userOpt.get()); // Login successful
        } else {
            return ResponseEntity.status(401).body("Invalid email or password!");
        }
    }
}
