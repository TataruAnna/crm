package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String productCode;
    @Column
    private String description;
    @Column
    private double buyingPrice;
    @Column
    private boolean isSpecialProduct;
//    @Column
//    private double sellingMargin;
    @Column
    private int stockNumber; // nu toate produsele sunt cu stoc

    @ManyToOne
    @JsonBackReference("category-product")
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JsonBackReference("product-supplier")
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("quotationItem-product")
    private List<QuotationItem> quotationItems;

    public Product() {
    }

    public Product(Long id, String productCode, String description, double buyingPrice, boolean isSpecialProduct, int stockNumber, Category category, Supplier supplier, List<QuotationItem> quotationItems) {
        this.id = id;
        this.productCode = productCode;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.isSpecialProduct = isSpecialProduct;
        this.stockNumber = stockNumber;
        this.category = category;
        this.supplier = supplier;
        this.quotationItems = quotationItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public boolean isSpecialProduct() {
        return isSpecialProduct;
    }

    public void setSpecialProduct(boolean specialProduct) {
        isSpecialProduct = specialProduct;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<QuotationItem> getQuotationItems() {
        return quotationItems;
    }

    public void setQuotationItems(List<QuotationItem> quotationItems) {
        this.quotationItems = quotationItems;
    }
}
