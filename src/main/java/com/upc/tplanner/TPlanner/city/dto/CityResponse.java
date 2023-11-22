package com.upc.tplanner.TPlanner.city.dto;

import com.upc.tplanner.TPlanner.country.model.Country;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CityResponse {

    private Long id;
    private String cityName;
    private Country country;
}
