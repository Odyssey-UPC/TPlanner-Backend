package com.upc.tplanner.TPlanner.travelItinerary.service.impl;

import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import com.upc.tplanner.TPlanner.touristService.repository.TouristServiceRepository;
import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import com.upc.tplanner.TPlanner.travelItinerary.repository.TravelItineraryRepository;
import com.upc.tplanner.TPlanner.travelItinerary.service.TravelItineraryService;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TravelItineraryServiceImpl implements TravelItineraryService {

    @Autowired
    TravelItineraryRepository travelItineraryRepository;

    @Autowired
    TouristServiceRepository touristServiceRepository;

    @Override
    public List<TravelItinerary> getAllTravelItineraries() {
        var travelItineraries = travelItineraryRepository.findAll();
        travelItineraries.forEach(travelItinerary -> travelItinerary.getTravelServices().sort(Comparator.comparing(TouristService::getServiceDate)));
        return travelItineraries;
    }

    @Override
    public TravelItinerary createTravelItinerary(TravelItinerary travelItinerary, List<Long> touristServicesIds) {
        validateTravelItinerary(travelItinerary);
        validateTouristServices(touristServicesIds);
        travelItinerary.setDateCreated(LocalDateTime.now());
        travelItinerary.setDateUpdated(LocalDateTime.now());
        travelItinerary.setTravelServices(touristServiceRepository.findAllById(touristServicesIds.stream().mapToLong(Long::valueOf).boxed().toList()));
        calculateTotalPrice(travelItinerary);
        return travelItineraryRepository.save(travelItinerary);
    }

    @Override
    public TravelItinerary getTravelItineraryById(Long id) {
        existTravelItineraryById(id);
        var travelItinerary = travelItineraryRepository.findById(id).get();
        travelItinerary.getTravelServices().sort(Comparator.comparing(TouristService::getServiceDate));
        return travelItinerary;
    }

    @Override
    public TravelItinerary updateTravelItinerary(Long id, TravelItinerary travelItinerary, List<Long> touristServicesIds) {
        existTravelItineraryById(id);
        validateTravelItinerary(travelItinerary);
        validateTouristServices(touristServicesIds);
        var travelItineraryToUpdate = travelItineraryRepository.findById(id).get();
        travelItineraryToUpdate.setItineraryName(travelItinerary.getItineraryName());
        travelItineraryToUpdate.setItineraryDescription(travelItinerary.getItineraryDescription());
        travelItineraryToUpdate.setTravelServices(touristServiceRepository.findAllById(touristServicesIds.stream().mapToLong(Long::valueOf).boxed().toList()));
        calculateTotalPrice(travelItineraryToUpdate);
        travelItineraryToUpdate.setDateUpdated(LocalDateTime.now());
        return travelItineraryRepository.save(travelItineraryToUpdate);
    }

    @Override
    public void deleteTravelItinerary(Long id) {
        existTravelItineraryById(id);
        var travelItineraryToDelete = travelItineraryRepository.findById(id).get();
        travelItineraryToDelete.getTravelServices().clear();
        travelItineraryRepository.save(travelItineraryToDelete);
        travelItineraryRepository.deleteById(id);
    }

    private void existTravelItineraryById(Long id) {
        if(!travelItineraryRepository.existsById(id)){
            throw new ResourceNotFoundException("Travel Itinerary not found");
        }
    }

    private void validateTravelItinerary(TravelItinerary travelItinerary) {
        if(travelItinerary.getItineraryName() == null || travelItinerary.getItineraryName().isEmpty()){
            throw new ValidationException("Travel Itinerary name is required");
        }
        if ( travelItinerary.getItineraryDescription() == null || travelItinerary.getItineraryDescription().isEmpty()){
            throw new ValidationException("Travel Itinerary description is required");
        }
    }

    private void validateTouristServices(List<Long> touristServicesIds) {
        touristServicesIds.forEach(id -> {
            if (!touristServiceRepository.existsById(id.longValue())){
                throw new ResourceNotFoundException("Tourist Service with id: " + id + " not found");
            }
        });
    }

    private void calculateTotalPrice(TravelItinerary travelItinerary) {
        if (travelItinerary.getTravelServices() != null && !travelItinerary.getTravelServices().isEmpty()){
            travelItinerary.setTotalPrice(travelItinerary.getTravelServices().stream().mapToDouble(TouristService::getPrice).sum());
        }
        else {
            travelItinerary.setTotalPrice(0.0);
        }
    }
}
