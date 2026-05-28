package com.smart.planner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testBackend() {
        return "Congratulations! Smart Resource Planner Backend is running successfully!";
    }
}