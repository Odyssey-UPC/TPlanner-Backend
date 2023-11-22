package com.upc.tplanner.TPlanner.touristService.repository;

import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristServiceRepository extends JpaRepository<TouristService, Long> {
    boolean existsById(Long touristServiceId);
}
