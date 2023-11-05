package com.upc.tplanner.TPlanner.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TouristResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String gender;
    private int travelPoints;
    private boolean hasPremium;
    @JsonProperty("user")
    UserResponse userResponse;
}
