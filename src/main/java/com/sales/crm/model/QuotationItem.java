package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "quotation_item")
public class QuotationItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double quantity;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonBackReference("quotationItem-product")
    private Product product;



    @ManyToOne
    @JoinColumn(name="quotation_id")
    @JsonBackReference("quotationItem-quotation")
    private Quotation quotation;
    //exemplu: o bucatarie, un dormitor, format din mai multe quotationItem-uri cum ar fii: 2coli de pal, 7 balamale , 5 sisteme de ridicat frontul


    public QuotationItem() {
    }

    public QuotationItem(Long id, Product product, Quotation quotation, Double quantity, Double price) {
        this.id = id;
        this.product = product;
        this.quotation = quotation;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
