package com.upc.tplanner.TPlanner.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "role", length = 30, nullable = false)
    private String role;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = true)
    private LocalDateTime dateUpdated;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "profile_link", nullable = false)
    private String profileLink;
}
