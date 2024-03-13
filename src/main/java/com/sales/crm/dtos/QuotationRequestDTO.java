package com.sales.crm.dtos;

public class QuotationRequestDTO {
    private String name;
    private double sellingMargin;
    private Long orderId;

    private Long clientId;
    private Long userId; //de refactorizat cu metoda findUserLoggedIn

    public QuotationRequestDTO(String name, double sellingMargin, Long orderId, Long clientId, Long userId) {
        this.name = name;
        this.sellingMargin = sellingMargin;
        this.orderId = orderId;
        this.clientId = clientId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellingMargin() {
        return sellingMargin;
    }

    public void setSellingMargin(double sellingMargin) {
        this.sellingMargin = sellingMargin;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
