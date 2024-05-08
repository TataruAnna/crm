package com.sales.crm.dtos;

import java.time.LocalDateTime;

public class OrderObsResponseDTO {
    private LocalDateTime createdAt;
    private String text;

    public OrderObsResponseDTO(LocalDateTime createdAt, String text) {
        this.createdAt = createdAt;
        this.text = text;
    }

    public OrderObsResponseDTO() {
    }

    public LocalDateTime getCreatedAt() {
        if(createdAt == null){
            return LocalDateTime.of(0000,00,00,00,00);
        }
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        if(text==null){
            return "No order observation yet";
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
