package com.upc.tplanner.TPlanner.travelItinerary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.tplanner.TPlanner.touristService.dto.TouristServiceResponse;
import lombok.Data;

import java.util.List;

@Data
public class TravelItineraryResponse {

        private Long id;
        private String itineraryName;
        private String itineraryDescription;
        private String dateCreated;
        private String dateUpdated;
        private Double totalPrice;
        @JsonProperty("tourist_services")
        private List<TouristServiceResponse> travelServices;
}

