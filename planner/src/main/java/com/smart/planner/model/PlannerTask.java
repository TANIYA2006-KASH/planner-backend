package com.smart.planner.model; 
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class PlannerTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private String status; // e.g., "PENDING", "IN_PROGRESS", "COMPLETED"

    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // --- No-Args Constructor ---
    public PlannerTask() {}

    // --- All-Args Constructor ---
    public PlannerTask(String title, String description, LocalDateTime deadline, String status, User user) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.user = user;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
