package com.upc.tplanner.TPlanner.touristService.model;

import com.upc.tplanner.TPlanner.city.model.City;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tourist-services")
public class TouristService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="city_id", nullable=false, foreignKey = @ForeignKey(name = "FK_TOURIST_SERVICE_CITY_ID"))
    private City city;

    @Column(name = "service_name", length = 60, nullable = false)
    private String serviceName;

    @Column(name = "service_description", length = 200, nullable = false)
    private String serviceDescription;

    @Column(name = "service_category", length = 60, nullable = false)
    private String serviceCategory;

    @Column(name = "service_date", nullable = false)
    private LocalDateTime serviceDate;

    @Column(name = "service_deadline", nullable = false)
    private LocalDateTime serviceDeadline;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="tourist_provider_id", nullable=false, foreignKey = @ForeignKey(name = "FK_TOURIST_SERVICE_TOURIST_PROVIDER_ID"))
    private TouristProvider touristProvider;

    @ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
    @CollectionTable(name="images", joinColumns=@JoinColumn(name="tourist_service_id"))
    @Column(name="image", nullable=false)
    private List<String> images;

}
