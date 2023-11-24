package com.upc.tplanner.TPlanner.city.controller;

import com.upc.tplanner.TPlanner.city.dto.CityRequest;
import com.upc.tplanner.TPlanner.city.dto.CityResponse;
import com.upc.tplanner.TPlanner.city.dto.mapper.CityMapper;
import com.upc.tplanner.TPlanner.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    CityService cityService;

    @Transactional(readOnly = true)
    @GetMapping("/cities")
    public ResponseEntity<List<CityResponse>> getAllCities(){
        var citiesResponse = CityMapper.INSTANCE.cityToCitiesResponse(cityService.getAllCities());
        return new ResponseEntity<>(citiesResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/cities/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable("id") Long id){
        var cityResponse = CityMapper.INSTANCE.cityToCityResponse(cityService.getCityById(id));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/cities/filterByCityName")
    public ResponseEntity<CityResponse> getCityByName(@RequestParam(value = "cityName") String cityName){
        var cityResponse = CityMapper.INSTANCE.cityToCityResponse(cityService.getCityByName(cityName));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/countries/{countryId}/cities")
    public ResponseEntity<CityResponse> createCity(@PathVariable(value = "countryId") Long countryId,
                                                   @RequestBody CityRequest cityRequest){
        var city = CityMapper.INSTANCE.cityRequestToCity(cityRequest);
        var cityCreated = cityService.createCity(countryId, city);
        var cityResponseCreated = CityMapper.INSTANCE.cityToCityResponse(cityCreated);
        return new ResponseEntity<>(cityResponseCreated, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/cities/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable(value = "id") Long id,
                                                   @RequestBody CityRequest cityRequest){
        var city = CityMapper.INSTANCE.cityRequestToCity(cityRequest);
        var cityUpdated = cityService.updateCity(id, city);
        var cityResponseUpdated = CityMapper.INSTANCE.cityToCityResponse(cityUpdated);
        return new ResponseEntity<>(cityResponseUpdated, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable(value = "id") Long id){
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
