package com.sales.crm.dtos;


import java.time.LocalDateTime;

public class MeetingObsResponseDTO {
    private LocalDateTime createdAt;
    private String text;

    public MeetingObsResponseDTO() {
    }

    public MeetingObsResponseDTO(LocalDateTime createdAt, String text) {
        this.createdAt = createdAt;
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
