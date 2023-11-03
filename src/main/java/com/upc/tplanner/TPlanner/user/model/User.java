package com.upc.tplanner.TPlanner.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
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
    private Date birthdate;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

}
