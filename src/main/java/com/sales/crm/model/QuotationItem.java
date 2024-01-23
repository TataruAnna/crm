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
    private Integer quantity;
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

    public QuotationItem(Long id, Integer quantity, Product product, Quotation quotation) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.quotation = quotation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
