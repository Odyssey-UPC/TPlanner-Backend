package com.upc.tplanner.TPlanner.city.service.impl;

import com.upc.tplanner.TPlanner.city.model.City;
import com.upc.tplanner.TPlanner.city.repository.CityRepository;
import com.upc.tplanner.TPlanner.city.service.CityService;
import com.upc.tplanner.TPlanner.country.repository.CountryRepository;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository  countryRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityByName(String cityName) {
        existsByCityName(cityName);
        return cityRepository.findByCityName(cityName);
    }

    @Override
    public City getCityById(Long id) {
        existsById(id);
        return cityRepository.findById(id).get();
    }


    @Override
    public City createCity(Long countryId, City city) {
        validateCity(city);
        existsCountryById(countryId);
        city.setCountry(countryRepository.findById(countryId).get());
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(Long id, City city) {
        existsById(id);
        validateCity(city);
        City cityToUpdate = cityRepository.findById(id).get();
        cityToUpdate.setCityName(city.getCityName());
        return cityRepository.save(cityToUpdate);
    }

    @Override
    public void deleteCity(Long id) {
        existsById(id);
        cityRepository.deleteById(id);
    }

    private void existsByCityName(String cityName) {
        if (!cityRepository.existsByCityName(cityName)) {
            throw new ResourceNotFoundException("City with name " + cityName + " not found");
        }
    }

    private void existsById(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new ResourceNotFoundException("City with id " + id + " not found");
        }
    }

    private void existsCountryById(Long countryId) {
        if (!countryRepository.existsById(countryId)) {
            throw new ResourceNotFoundException("Country with id " + countryId + " not found");
        }
    }

    private void validateCity(City city) {
        if (city.getCityName() == null || city.getCityName().isEmpty()) {
            throw new ValidationException("City name is required");
        }
    }
}
