package com.upc.tplanner.TPlanner.user.controller;

import com.upc.tplanner.TPlanner.user.dto.TouristProviderRequest;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristProviderMapper;
import com.upc.tplanner.TPlanner.user.service.TouristProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TouristProviderController {

    @Autowired
    TouristProviderService touristProviderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/touristProviders/{id}")
    public ResponseEntity<TouristProviderResponse> getTouristProviderById(@PathVariable(value = "id") Long id){
        var touristProvider = touristProviderService.getTouristProviderById(id);
        var touristProviderResponse = TouristProviderMapper.INSTANCE.TouristProviderToTouristProviderResponse(touristProvider);
        return new ResponseEntity<>(touristProviderResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/touristProviders")
    public ResponseEntity<List<TouristProviderResponse>> getAllTouristProviders(){
        var touristProvidersResponse = TouristProviderMapper.INSTANCE.touristProvidersToTouristProvidersResponse(touristProviderService.getAllTouristProviders());
        return new ResponseEntity<>(touristProvidersResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/touristProviders")
    public ResponseEntity<TouristProviderResponse> createTouristProvider(@RequestBody TouristProviderRequest touristProviderRequest){
        var user = TouristProviderMapper.INSTANCE.TouristProviderRequestToUser(touristProviderRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var touristProvider = TouristProviderMapper.INSTANCE.touristProviderRequestToTouristProvider(touristProviderRequest);
        var touristProviderCreated = touristProviderService.createTouristProvider(user, touristProvider);
        var touristProviderResponse = TouristProviderMapper.INSTANCE.TouristProviderToTouristProviderResponse(touristProviderCreated);
        return new ResponseEntity<>(touristProviderResponse, HttpStatus.CREATED);
    }

}
