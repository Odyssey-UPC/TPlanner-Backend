package com.upc.tplanner.TPlanner.travelItinerary.service;

import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;

import java.util.List;

public interface TravelItineraryService {

    List<TravelItinerary> getAllTravelItineraries();
    TravelItinerary createTravelItinerary(TravelItinerary travelItinerary, List<Long> touristServicesIds);
    TravelItinerary getTravelItineraryById(Long id);
    TravelItinerary updateTravelItinerary(Long id,TravelItinerary travelItinerary, List<Long> touristServicesIds);
    void deleteTravelItinerary(Long id);
}
