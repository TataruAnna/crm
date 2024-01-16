package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
//OFERTA
@Entity
public class Quotation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonBackReference("quotation-client")
    private Client client;
    @OneToMany(mappedBy = "quotation",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("quotationItem-quotation")
    private List<QuotationItem> quotationItems;
}
