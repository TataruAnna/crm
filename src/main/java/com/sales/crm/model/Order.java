package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private OrderStatus orderStatus;


    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonBackReference("order-client")
    private Client client;

    @OneToMany(mappedBy = "order",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("order-user")
    private List<User> users;


    //one-to-many cu quotation (o comanda poate sa contina una sau mai multe oferte de mobilier, de ex: bucatarie + dormitor)
    @OneToMany(mappedBy = "order",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("order-quotation")
    private List<Quotation> quotations;


    @OneToMany(mappedBy = "order",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("order-orderObs")
    private List<OrderObservation> orderObservations;


    //ar trebui sa se calculeze aici in service pretul total, un order poate avea mai multe quotations


    public Order() {
    }

    public Order(Long id, String name, OrderStatus orderStatus, Client client, List<User> users, List<Quotation> quotations, List<OrderObservation> orderObservations) {
        this.id = id;
        this.name = name;
        this.orderStatus = orderStatus;
        this.client = client;
        this.users = users;
        this.quotations = quotations;
        this.orderObservations = orderObservations;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public List<OrderObservation> getOrderObservations() {
        return orderObservations;
    }

    public void setOrderObservations(List<OrderObservation> orderObservations) {
        this.orderObservations = orderObservations;
    }
}
