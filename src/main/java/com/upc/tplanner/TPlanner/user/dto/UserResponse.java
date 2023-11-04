package com.upc.tplanner.TPlanner.user.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class UserResponse {
    private String username;
    private String role;
    private LocalDate birthdate;
    private String email;
    private String profileLink;
}
