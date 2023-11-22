package com.upc.tplanner.TPlanner.touristService.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TouristServiceRequest {
    private String serviceName;
    private String serviceDescription;
    private String serviceCategory;
    private LocalDateTime serviceDate;
    private LocalDateTime serviceDeadline;
    private Double price;
    private Integer capacity;
    private String content;
}
