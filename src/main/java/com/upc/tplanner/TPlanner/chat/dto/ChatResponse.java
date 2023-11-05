package com.upc.tplanner.TPlanner.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import com.upc.tplanner.TPlanner.user.dto.TouristResponse;
import lombok.Data;

@Data
public class ChatResponse {
    @JsonProperty("tourist")
    private TouristResponse touristResponse;
    @JsonProperty("touristProvider")
    private TouristProviderResponse touristProviderResponse;
    private Long sentBy;
    private String message;
}
