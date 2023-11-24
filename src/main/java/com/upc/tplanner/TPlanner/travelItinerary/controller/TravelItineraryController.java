package com.upc.tplanner.TPlanner.travelItinerary.controller;

import com.upc.tplanner.TPlanner.travelItinerary.dto.TravelItineraryRequest;
import com.upc.tplanner.TPlanner.travelItinerary.dto.TravelItineraryResponse;
import com.upc.tplanner.TPlanner.travelItinerary.dto.mapper.TravelItineraryMapper;
import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import com.upc.tplanner.TPlanner.travelItinerary.service.TravelItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TravelItineraryController {

    @Autowired
    TravelItineraryService travelItineraryService;

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN')")
    @GetMapping("/travelItineraries")
    public ResponseEntity<List<TravelItineraryResponse>> getAllTravelItineraries(){
        var travelItineraries = travelItineraryService.getAllTravelItineraries();
        return new ResponseEntity<>(TravelItineraryMapper.INSTANCE.travelItinerariesToTravelItineraries(travelItineraries),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN')")
    @GetMapping("/travelItineraries/{id}")
    public ResponseEntity<TravelItineraryResponse> getTravelItineraryById(@PathVariable("id") Long id){
        var travelItinerary = travelItineraryService.getTravelItineraryById(id);
        return new ResponseEntity<>(TravelItineraryMapper.INSTANCE.travelItineraryToTravelItineraryResponse(travelItinerary),HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN')")
    @PostMapping("/travelItineraries")
    public ResponseEntity<TravelItineraryResponse> createTravelItinerary(@RequestBody TravelItineraryRequest travelItineraryRequest){
        var travelItinerary = TravelItineraryMapper.INSTANCE.travelItineraryRequestToTravelItinerary(travelItineraryRequest);
        var createdTravelItinerary = travelItineraryService.createTravelItinerary(travelItinerary, travelItineraryRequest.getTouristServicesIds());
        return new ResponseEntity<>(TravelItineraryMapper.INSTANCE.travelItineraryToTravelItineraryResponse(createdTravelItinerary),HttpStatus.CREATED);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN')")
    @PutMapping("/travelItineraries/{id}")
    public ResponseEntity<TravelItineraryResponse> updateTravelItinerary(@PathVariable("id") Long id, @RequestBody TravelItineraryRequest travelItineraryRequest){
        var travelItinerary = TravelItineraryMapper.INSTANCE.travelItineraryRequestToTravelItinerary(travelItineraryRequest);
        var updatedTravelItinerary = travelItineraryService.updateTravelItinerary(id, travelItinerary, travelItineraryRequest.getTouristServicesIds());
        return new ResponseEntity<>(TravelItineraryMapper.INSTANCE.travelItineraryToTravelItineraryResponse(updatedTravelItinerary),HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('TOURIST', 'ADMIN')")
    @DeleteMapping("/travelItineraries/{id}")
    public ResponseEntity<Void> deleteTravelItinerary(@PathVariable("id") Long id){
        travelItineraryService.deleteTravelItinerary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
