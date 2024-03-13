package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//OFERTA
@Entity
@Table(name = "quotation")
public class Quotation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name; //ex: mobilier de bucatarie
    @Column
    private Double sellingMargin;

    @Column
    private Double totalPrice;

    @Column
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonBackReference("order-quotation")
    private Order order;

    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonBackReference("quotation-client")
    private Client client;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference("quotation-user")
    private User user;

    @OneToMany(mappedBy = "quotation",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("quotationItem-quotation")
    private List<QuotationItem> quotationItems;

    //exemplu: o bucatarie, un dormitor, format din mai multe quotationItem-uri cum ar fii: 2coli de pal, 7 balamale , 5 sisteme de ridicat frontul


    public Quotation() {
    }

    public Quotation(Long id, String name, Double sellingMargin, Order order, Client client, List<QuotationItem> quotationItems, User user, Double totalPrice, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.sellingMargin = sellingMargin;
        this.order = order;
        this.client = client;
        this.quotationItems = quotationItems;
        this.user = user;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;

    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSellingMargin() {
        return sellingMargin;
    }

    public void setSellingMargin(Double sellingMargin) {
        this.sellingMargin = sellingMargin;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<QuotationItem> getQuotationItems() {
        return quotationItems;
    }

    public void setQuotationItems(List<QuotationItem> quotationItems) {
        this.quotationItems = quotationItems;
    }
}
