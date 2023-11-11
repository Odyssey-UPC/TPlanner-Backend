package com.upc.tplanner.TPlanner.country.service.impl;

import com.upc.tplanner.TPlanner.country.model.Country;
import com.upc.tplanner.TPlanner.country.repository.CountryRepository;
import com.upc.tplanner.TPlanner.country.service.CountryService;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long countryId) {
        existsCountry(countryId);
        return countryRepository.findById(countryId).get();
    }

    @Override
    public Country createCountry(Country country) {
        validateCountry(country);
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Long countryId,Country country) {
        existsCountry(countryId);
        validateCountry(country);
        var countryToUpdate = countryRepository.findById(countryId).get();
        countryToUpdate.setCountryName(country.getCountryName());
        return countryRepository.save(countryToUpdate);
    }

    @Override
    public void deleteCountry(Long countryId) {
        existsCountry(countryId);
        countryRepository.deleteById(countryId);
    }

    private void existsCountry(long countryId) {
        if (!countryRepository.existsById(countryId)) {
            throw new ResourceNotFoundException("Country not found");
        }
    }

    private void validateCountry(Country country) {
        if (country.getCountryName() == null || country.getCountryName().isEmpty()) {
            throw new ValidationException("Country name is required");
        }
    }

}
