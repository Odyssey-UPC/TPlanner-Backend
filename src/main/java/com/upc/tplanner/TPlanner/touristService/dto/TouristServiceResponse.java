package com.upc.tplanner.TPlanner.touristService.dto;

import com.upc.tplanner.TPlanner.city.model.City;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import jakarta.persistence.*;
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

    private TouristProvider touristProvider;

    private List<String> images;
}
