package com.sales.crm.dtos;

public class ClientSimpleResponseDTO {
    private String name;
    private String userName;
    private Long quotations;
    private Long orders;
    private Long meetings;

    public ClientSimpleResponseDTO() {
    }

    public ClientSimpleResponseDTO(String name, String userName, Long quotations, Long orders, Long meetings) {
        this.name = name;
        this.userName = userName;
        this.quotations = quotations;
        this.orders = orders;
        this.meetings = meetings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getQuotations() {
        return quotations;
    }

    public void setQuotations(Long quotations) {
        this.quotations = quotations;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public Long getMeetings() {
        return meetings;
    }

    public void setMeetings(Long meetings) {
        this.meetings = meetings;
    }
}
