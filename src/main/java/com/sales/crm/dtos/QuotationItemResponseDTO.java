package com.sales.crm.dtos;

public class QuotationItemResponseDTO {
    private String productName;
    private Double price;
    private Double quantity;
    private Double totalPrice;

    public QuotationItemResponseDTO() {
    }

    public QuotationItemResponseDTO(String productName, Double price, Double quantity, Double totalPrice) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
