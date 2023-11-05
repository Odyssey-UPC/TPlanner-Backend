package com.upc.tplanner.TPlanner.chat.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChatRequest {
    private Long sentBy;
    private String message;
}
