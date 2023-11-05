package com.upc.tplanner.TPlanner.chat.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {
    @EmbeddedId
    private ChatId id;

    @Column(name = "sent_by", nullable = false)
    private Long sentBy;

    @Column(name = "message", length = 1000, nullable = false)
    private String message;
}
