package com.upc.tplanner.TPlanner.user.repository;

import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristProviderRepository extends JpaRepository<TouristProvider, Long> {
    boolean existsTouristProviderById(Long id);

    TouristProvider findTouristProviderById(Long id);

}
