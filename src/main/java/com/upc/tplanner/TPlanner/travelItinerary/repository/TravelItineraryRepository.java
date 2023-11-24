package com.upc.tplanner.TPlanner.travelItinerary.repository;

import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelItineraryRepository extends JpaRepository<TravelItinerary, Long> {

    boolean existsById(Long id);

}
