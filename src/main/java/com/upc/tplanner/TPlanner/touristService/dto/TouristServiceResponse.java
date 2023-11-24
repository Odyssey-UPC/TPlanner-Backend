package com.upc.tplanner.TPlanner.touristService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.tplanner.TPlanner.city.model.City;
import com.upc.tplanner.TPlanner.user.dto.TouristProviderResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TouristServiceResponse {

    private Long id;

    private City city;

    private String serviceName;

    private String serviceDescription;

    private String serviceCategory;

    private LocalDateTime serviceDate;

    private LocalDateTime serviceDeadline;

    private Double price;

    private Integer capacity;

    private String content;

    @JsonProperty("touristProvider")
    private TouristProviderResponse touristProviderResponse;

    private List<String> images;
}
