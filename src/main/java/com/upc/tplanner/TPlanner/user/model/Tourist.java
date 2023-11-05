package com.upc.tplanner.TPlanner.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tourists")
public class Tourist {
    @Id
    private Long id;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "nationality", length = 20)
    private String nationality;

    @Column(name = "gender", length = 1, nullable = false)
    private String gender;

    @Column(name = "travel_points")
    private int travelPoints;

    @Column(name = "has_premium")
    private boolean hasPremium;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TOURIST_USER_ID"))
    private User user;
}
