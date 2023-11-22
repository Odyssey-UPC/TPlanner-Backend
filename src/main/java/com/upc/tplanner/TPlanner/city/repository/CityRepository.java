package com.upc.tplanner.TPlanner.city.repository;

import com.upc.tplanner.TPlanner.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByCityName(String cityName);

    boolean existsById(Long id);

    City findByCityName(String cityName);

}
