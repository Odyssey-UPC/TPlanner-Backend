package com.upc.tplanner.TPlanner.city.service;

import com.upc.tplanner.TPlanner.city.model.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();
    City getCityByName(String cityName);
    City getCityById(Long id);
    City createCity(Long countryId, City city);
    City updateCity(Long id, City city);
    void deleteCity(Long id);

}
