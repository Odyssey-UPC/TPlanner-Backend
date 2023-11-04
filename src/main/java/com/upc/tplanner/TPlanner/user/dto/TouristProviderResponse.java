package com.upc.tplanner.TPlanner.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TouristProviderResponse {
    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String websiteLink;
    @JsonProperty("user")
    private UserResponse userResponse;

}
