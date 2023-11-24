package com.upc.tplanner.TPlanner.user.controller;

import com.upc.tplanner.TPlanner.user.dto.TouristRequest;
import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import com.upc.tplanner.TPlanner.user.dto.mapper.TouristMapper;
import com.upc.tplanner.TPlanner.user.dto.mapper.UserMapper;
import com.upc.tplanner.TPlanner.user.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TouristController {

    @Autowired
    TouristService touristService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/tourists")
    public ResponseEntity<List<TouristResponse>> getAllTourists(){
        var touristsResponse = TouristMapper.INSTANCE.TouristsToTouristsResponse(touristService.getAllTourists());
        return new ResponseEntity<List<TouristResponse>>(touristsResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/tourists/{id}")
    public ResponseEntity<TouristResponse> getTouristById(@PathVariable("id") Long id){
        var touristResponse = TouristMapper.INSTANCE.TouristToTouristResponse(touristService.getTouristById(id));
        return new ResponseEntity<TouristResponse>(touristResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/tourists")
    public ResponseEntity<TouristResponse> createTourist(@RequestBody TouristRequest touristRequest){
        var user = TouristMapper.INSTANCE.TouristRequestToUser(touristRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var tourist = TouristMapper.INSTANCE.TouristRequestToTourist(touristRequest);
        var touristCreated = touristService.createTourist(user, tourist);
        var touristResponse = TouristMapper.INSTANCE.TouristToTouristResponse(touristCreated);
        return new ResponseEntity<TouristResponse>(touristResponse, HttpStatus.CREATED);
    }

}
