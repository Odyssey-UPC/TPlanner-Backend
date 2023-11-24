package com.upc.tplanner.TPlanner.operation.model;

import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import com.upc.tplanner.TPlanner.user.model.Tourist;
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
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;
    @ManyToOne
    @JoinColumn(name = "travel_itinerary_id", nullable = false)
    private TravelItinerary travelItinerary;
    @Column(name = "operation_date", nullable = false)
    private LocalDateTime operationDate;
    @Column(name = "operation_type", length = 30, nullable = false)
    private String operationType;


}
