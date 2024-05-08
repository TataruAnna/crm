package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "user-order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<User> users;


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

    public Order(Long id, String name, OrderStatus orderStatus, Client client, Set<User> users, List<Quotation> quotations, List<OrderObservation> orderObservations) {
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

    public Set<User> getUsers() {
        if(users == null ){
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        if(users == null){
            users = new HashSet<>();
        }
        this.users = users;
    }

    public List<Quotation> getQuotations() {
        if(quotations == null){
            quotations = new ArrayList<>();
        }
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public List<OrderObservation> getOrderObservations() {
        if(orderObservations == null){
            orderObservations = new ArrayList<>();
        }
        return orderObservations;
    }

    public void setOrderObservations(List<OrderObservation> orderObservations) {
        this.orderObservations = orderObservations;
    }
}
