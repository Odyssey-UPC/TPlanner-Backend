package com.upc.tplanner.TPlanner.country.controller;

import com.upc.tplanner.TPlanner.country.dto.CountryRequest;
import com.upc.tplanner.TPlanner.country.dto.CountryResponse;
import com.upc.tplanner.TPlanner.country.dto.mapper.CountryMapper;
import com.upc.tplanner.TPlanner.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Transactional(readOnly = true)
    @GetMapping("/countries")
    public ResponseEntity<List<CountryResponse>> getAllCountries() {
        var countriesResponse = CountryMapper.INSTANCE.countriesToCountriesResponse(countryService.getAllCountries());
        return new ResponseEntity<>(countriesResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable(value = "id") long countryId) {
        var countryResponse = CountryMapper.INSTANCE.countryToCountryResponse(countryService.getCountryById(countryId));
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/countries")
    public ResponseEntity<CountryResponse> createCountry(@RequestBody CountryRequest countryRequest) {
        var country = CountryMapper.INSTANCE.countryRequestToCountry(countryRequest);
        var countryCreated = countryService.createCountry(country);
        return new ResponseEntity<>(CountryMapper.INSTANCE.countryToCountryResponse(countryCreated), HttpStatus.CREATED);
    }
    @Transactional
    @PutMapping("/countries/{id}")
    public ResponseEntity<CountryResponse> updateCountry(@PathVariable(value = "id") long countryId,
                                                         @RequestBody CountryRequest countryRequest) {
        var country = CountryMapper.INSTANCE.countryRequestToCountry(countryRequest);
        var countryUpdated = countryService.updateCountry(countryId, country);
        return new ResponseEntity<>(CountryMapper.INSTANCE.countryToCountryResponse(countryUpdated), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable(value = "id") long countryId) {
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
