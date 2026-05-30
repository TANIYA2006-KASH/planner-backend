package com.smart.planner.repository;

import com.smart.planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Isse hum email ke zariye user ko dhoodh payenge
    Optional<User> findByEmail(String email);
}
