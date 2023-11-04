package com.upc.tplanner.TPlanner.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TouristRequest {

    private String username;
    private String password;
    private LocalDate birthdate;
    @Email
    private String email;
    private String profileLink;
    private String firstName;
    private String lastName;
    private String nationality;
    private String gender;
}
