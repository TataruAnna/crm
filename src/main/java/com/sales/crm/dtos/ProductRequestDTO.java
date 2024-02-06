package com.sales.crm.dtos;

public class ProductRequestDTO {
    private String productCode;

    private String description;

    private Double buyingPrice;

    private Boolean isSpecialProduct;

    private Integer stockNumber;

    private Long categoryId;

    private Long supplierId;

    public ProductRequestDTO(String productCode, String description, Double buyingPrice, Boolean isSpecialProduct, Double sellingMargin, Integer stockNumber, Long categoryId, Long supplierId) {
        this.productCode = productCode;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.isSpecialProduct = isSpecialProduct;
        this.stockNumber = stockNumber;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Boolean isSpecialProduct() {
        return isSpecialProduct;
    }

    public void setSpecialProduct(Boolean specialProduct) {
        isSpecialProduct = specialProduct;
    }


    public Integer getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
