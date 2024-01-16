package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class QuotationItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;





    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonBackReference("quotationItem-product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="quotation_id")
    @JsonBackReference("quotationItem-quotation")
    private Quotation quotation;
}
