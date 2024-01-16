package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
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
}
