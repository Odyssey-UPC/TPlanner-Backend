package com.upc.tplanner.TPlanner.user.repository;

import com.upc.tplanner.TPlanner.user.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository extends JpaRepository<Tourist, Long> {

    boolean existsTouristById(Long id);
    Tourist findTouristById(Long id);
}
