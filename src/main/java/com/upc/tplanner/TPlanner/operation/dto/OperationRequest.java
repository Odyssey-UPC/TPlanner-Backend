package com.upc.tplanner.TPlanner.operation.dto;

import lombok.Data;

@Data
public class OperationRequest {
    private Long touristId;
    private Long travelItineraryId;
    private String operationType;
}
