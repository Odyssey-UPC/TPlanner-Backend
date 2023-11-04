package com.upc.tplanner.TPlanner.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TouristProviderRequest {

    private String username;
    private String password;
    private LocalDate birthdate;
    @Email
    private String email;
    private String profileLink;
    private String organizationName;
    private String organizationDescription;
    private String websiteLink;

}
