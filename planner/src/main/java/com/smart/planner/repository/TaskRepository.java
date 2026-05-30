package com.smart.planner.repository;

import com.smart.planner.model.PlannerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<PlannerTask, Long> {
    // Isse hum kisi ek user ke saare tasks ek sath nikal sakenge
    List<PlannerTask> findByUserId(Long userId);
}
