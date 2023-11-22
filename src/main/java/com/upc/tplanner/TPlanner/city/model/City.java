package com.upc.tplanner.TPlanner.city.model;

import com.upc.tplanner.TPlanner.country.model.Country;
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
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city_name", length = 60, nullable = false)
    private String cityName;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="country_id", nullable=false, foreignKey = @ForeignKey(name = "FK_CITY_COUNTRY_ID"))
    private Country country;
}
