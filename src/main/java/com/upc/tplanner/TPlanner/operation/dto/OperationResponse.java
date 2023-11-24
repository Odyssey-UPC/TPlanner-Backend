package com.upc.tplanner.TPlanner.operation.dto;

import com.upc.tplanner.TPlanner.travelItinerary.dto.TravelItineraryResponse;
import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationResponse {
    TravelItineraryResponse travelItinerary;
    TouristResponse tourist;
    LocalDateTime operationDate;
    String operationType;
}
