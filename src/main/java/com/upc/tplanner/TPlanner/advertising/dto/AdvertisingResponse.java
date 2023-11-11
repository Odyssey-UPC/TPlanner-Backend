package com.upc.tplanner.TPlanner.advertising.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdvertisingResponse {
    private Long id;
    private String name;
    private String thumbnail;
    private Long views;
    private Long clicks;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String serviceName;
    private String redirectTo;
    private String status;
    private Double amountInvested;
    @JsonProperty("tourist_provider")
    private TouristProviderResponse touristProviderResponse;
}
