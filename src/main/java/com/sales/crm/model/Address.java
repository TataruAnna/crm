package com.sales.crm.model;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String country;
    @Column
    private String town;
    @Column
    private String street;
    @Column
    private int number;
    @Column
    private String details;

    @OneToOne(mappedBy = "address")
    private Supplier supplier;
    @OneToOne(mappedBy = "address")
    private Client client;



}
