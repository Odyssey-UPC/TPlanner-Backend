package com.upc.tplanner.TPlanner.travelItinerary.dto;

import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TravelItineraryRequest {

    private String itineraryName;
    private String itineraryDescription;
    private List<Long> touristServicesIds;

}
