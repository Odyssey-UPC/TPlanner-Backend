package com.upc.tplanner.TPlanner.chat.model;

import com.upc.tplanner.TPlanner.user.model.Tourist;
import com.upc.tplanner.TPlanner.user.model.TouristProvider;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class ChatId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "tourist_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TOURIST_ID"))
    private Tourist tourist;
    @ManyToOne
    @JoinColumn(name = "tourist_provider_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TOURIST_PROVIDER_ID"))
    private TouristProvider touristProvider;
    private LocalDateTime dateCreated;
}
