package com.upc.tplanner.TPlanner.travelItinerary.model;

import com.upc.tplanner.TPlanner.touristService.model.TouristService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "travel-itinerary")
public class TravelItinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "itinerary_name", length = 60, nullable = false)
    private String itineraryName;
    @Column(name = "itinerary_description", length = 500, nullable = false)
    private String itineraryDescription;
    @Column(name = "itinerary_date", nullable = false)
    private LocalDateTime dateCreated;
    @Column(name = "itinerary_deadline", nullable = false)
    private LocalDateTime dateUpdated;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "itinerary_services",
            joinColumns = @JoinColumn(name = "itinerary_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<TouristService> travelServices;
}
