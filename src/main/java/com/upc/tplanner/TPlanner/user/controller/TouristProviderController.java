package com.upc.tplanner.TPlanner.user.controller;

import com.upc.tplanner.TPlanner.user.dto.TouristProviderRequest;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristProviderMapper;
import com.upc.tplanner.TPlanner.user.service.TouristProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tplanner/v1")
public class TouristProviderController {

    @Autowired
    TouristProviderService touristProviderService;

    @GetMapping(value = "/touristProviders")
    public ResponseEntity<List<TouristProviderResponse>> getAllTouristProviders(){
        var touristProvidersResponse = TouristProviderMapper.INSTANCE.TouristProvidersToTouristProvidersResponse(touristProviderService.getAllTouristProviders());
        return new ResponseEntity<>(touristProvidersResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/touristProviders")
    public ResponseEntity<TouristProviderResponse> createTouristProvider(@RequestBody TouristProviderRequest touristProviderRequest){
        var user = TouristProviderMapper.INSTANCE.TouristProviderRequestToUser(touristProviderRequest);
        var touristProvider = TouristProviderMapper.INSTANCE.TouristProviderRequestToTouristProvider(touristProviderRequest);
        var touristProviderCreated = touristProviderService.createTouristProvider(user, touristProvider);
        var touristProviderResponse = TouristProviderMapper.INSTANCE.TouristProviderToTouristProviderResponse(touristProviderCreated);
        return new ResponseEntity<>(touristProviderResponse, HttpStatus.CREATED);
    }

}
