package com.upc.tplanner.TPlanner.country.service;

import com.upc.tplanner.TPlanner.country.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryById(Long countryId);
    Country createCountry(Country country);
    Country updateCountry(Long countryId, Country country);
    void deleteCountry(Long countryId);
}
