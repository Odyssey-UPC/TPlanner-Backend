package com.upc.tplanner.TPlanner.user.repository;

import com.upc.tplanner.TPlanner.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
    Optional<User> findByUsername(String username);
    boolean existsById(Long id);
}
