package com.sales.crm.dtos;

import java.time.LocalDateTime;

public class QuotationResponseDTO {
    private String name; //ex: mobilier de bucatarie
    private Double sellingMargin;
    private Double totalPrice;

    private LocalDateTime dateCreated;

    private String userName;
    private String clientName;

    public QuotationResponseDTO() {
    }

    public QuotationResponseDTO(String name, Double sellingMargin, Double totalPrice, LocalDateTime dateCreated, String userName, String clientName) {
        this.name = name;
        this.sellingMargin = sellingMargin;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
        this.userName = userName;
        this.clientName = clientName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSellingMargin() {
        return sellingMargin;
    }

    public void setSellingMargin(Double sellingMargin) {
        this.sellingMargin = sellingMargin;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
