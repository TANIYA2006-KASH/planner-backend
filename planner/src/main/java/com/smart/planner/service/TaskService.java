package com.smart.planner.service;

import com.smart.planner.model.PlannerTask;
import com.smart.planner.model.User;
import com.smart.planner.repository.TaskRepository;
import com.smart.planner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Naya Task Create Karne Ke Liye 
    public PlannerTask createTask(PlannerTask task, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        task.setUser(user); // Task ko us user se connect kiya
        if (task.getStatus() == null) {
            task.setStatus("PENDING"); // By default status PENDING 
        }
        return taskRepository.save(task);
    }

    // 2. Kisi ek User ke saare Tasks dekhne ke liye 
    public List<PlannerTask> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // 3. Task ka Status Update Karne Ke Liye 
    public PlannerTask updateTaskStatus(Long taskId, String newStatus) {
        PlannerTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        
        task.setStatus(newStatus.toUpperCase());
        return taskRepository.save(task);
    }

    // 4. Task Delete Karne Ke Liye
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }
}
