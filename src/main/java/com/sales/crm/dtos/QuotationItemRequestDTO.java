package com.sales.crm.dtos;

public class QuotationItemRequestDTO {
    private Double quantity;
    private Long productId;
    private Long quotationId;

    public QuotationItemRequestDTO(Double quantity, Long productId, Long quotationId) {
        this.quantity = quantity;
        this.productId = productId;
        this.quotationId = quotationId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
    }
}
