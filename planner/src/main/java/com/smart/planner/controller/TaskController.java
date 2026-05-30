package com.smart.planner.controller;

import com.smart.planner.model.PlannerTask;
import com.smart.planner.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // 1. Endpoint: Naya Task Create Karne Ke Liye (POST Request)
    // URL: http://localhost:8080/api/tasks/create?userId=1
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody PlannerTask task, @RequestParam Long userId) {
        try {
            PlannerTask createdTask = taskService.createTask(task, userId);
            return ResponseEntity.ok(createdTask);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2. Endpoint: Kisi specific User ke saare Tasks dekhne ke liye (GET Request)
    // URL: http://localhost:8080/api/tasks/user/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlannerTask>> getTasksByUser(@PathVariable Long userId) {
        List<PlannerTask> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    // 3.  (PUT Request)
    // URL: http://localhost:8080/api/tasks/1/status?newStatus=COMPLETED
    @PutMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId, @RequestParam String newStatus) {
        try {
            PlannerTask updatedTask = taskService.updateTaskStatus(taskId, newStatus);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. Endpoint: Task Delete Karne Ke Liye 
    // URL: http://localhost:8080/api/tasks/1
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Task deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}