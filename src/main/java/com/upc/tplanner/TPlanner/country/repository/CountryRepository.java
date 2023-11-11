package com.upc.tplanner.TPlanner.country.repository;

import com.upc.tplanner.TPlanner.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
