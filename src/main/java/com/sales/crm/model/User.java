package com.sales.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String pass;

    @Column
    private String email;

    @Column
    private Boolean isActive;

    @OneToMany(mappedBy = "user",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("client-user")
    private List<Client> clients;

    @ManyToMany(mappedBy ="users",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference("users-roles")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("meeting-user")
    private List<Meeting> meetings;

    @OneToMany(mappedBy = "user",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("quotation-user")
    private List<Quotation> quotations;

    @ManyToMany(mappedBy = "users" )
//    @JsonBackReference("users-roles")
    private Set<Order> orders;

    public User() {
    }

    public User(Long id, String name, String pass, String email, List<Client> clients, Set<Role> roles, List<Meeting> meetings, Set<Order> orders, List<Quotation> quotations, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.clients = clients;
        this.roles = roles;
        this.meetings = meetings;
        this.orders = orders;
        this.quotations=quotations;
        this.isActive = true;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        if(quotations == null){
            quotations = new ArrayList<>();
        }
        this.quotations = quotations;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Set<Role> getRoles() {
        if(roles==null){
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        if(orders == null){
            orders = new HashSet<>();
        }
        this.orders = orders;
    }
}
