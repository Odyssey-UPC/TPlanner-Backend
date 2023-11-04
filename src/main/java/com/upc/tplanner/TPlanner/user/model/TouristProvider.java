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
@Table(name = "tourist_providers")
public class TouristProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name", length = 25, nullable = false)
    private String organizationName;

    @Column(name = "organization_description", length = 500, nullable = false)
    private String organizationDescription;

    @Column(name = "website_link", nullable = false)
    private String websiteLink;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TOURIST_USER_ID"))
    private User user;
}
