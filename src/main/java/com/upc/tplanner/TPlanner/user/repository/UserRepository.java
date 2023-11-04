package com.upc.tplanner.TPlanner.user.repository;

import com.upc.tplanner.TPlanner.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);
}
