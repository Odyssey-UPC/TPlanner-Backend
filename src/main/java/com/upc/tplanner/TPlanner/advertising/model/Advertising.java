package com.upc.tplanner.TPlanner.advertising.model;

import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advertisements")
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "views", nullable = false)
    private Long views;

    @Column(name = "clicks", nullable = false)
    private Long clicks;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "service_name", length = 40, nullable = false)
    private String serviceName;

    @Column(name = "redirect_to", nullable = false)
    private String redirectTo;

    @Column(name = "status", length = 40, nullable = false)
    private String status;

    @Column(name = "amount_invested", nullable = false)
    private Double amountInvested;

    @ManyToOne
    @JoinColumn(name = "tourist_provider_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TOURIST_PROVIDER_ID"))
    TouristProvider touristProvider;
}
