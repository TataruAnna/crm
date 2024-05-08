package com.sales.crm.dtos;

import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.exceptions.ResourceNotValidException;
import com.sales.crm.model.OrderStatus;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class OrderResponseDTO {

    private String name;

    private OrderStatus orderStatus;

    private List<String> userNames;
    private List<OrderObsResponseDTO> orderObsResponseDTOS;

    public OrderResponseDTO(String name, OrderStatus orderStatus, List<String> userNames, List<OrderObsResponseDTO> orderObsResponseDTOS) {
        this.name = name;
        this.orderStatus = orderStatus;
        this.userNames = userNames;
        this.orderObsResponseDTOS = orderObsResponseDTOS;
    }

    public OrderResponseDTO() {
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

    public List<String> getUserNames() {
        if(userNames ==null){
            ResourceNotValidException resourceNotValidException = new ResourceNotValidException("There is a problem, there is no user asigned");
        }
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public List<OrderObsResponseDTO> getOrderObsResponseDTOS() {
        if(orderObsResponseDTOS ==null){
            orderObsResponseDTOS = new ArrayList<>();
        }
        return orderObsResponseDTOS;
    }

    public void setOrderObsResponseDTOS(List<OrderObsResponseDTO> orderObsResponseDTOS) {
        this.orderObsResponseDTOS = orderObsResponseDTOS;
    }
}
