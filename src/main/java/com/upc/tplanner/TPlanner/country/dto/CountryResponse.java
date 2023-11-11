package com.upc.tplanner.TPlanner.country.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CountryResponse {
    private Long id;
    private String countryName;
}
