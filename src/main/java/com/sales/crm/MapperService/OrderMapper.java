package com.sales.crm.MapperService;

import com.sales.crm.dtos.OrderObsResponseDTO;
import com.sales.crm.dtos.OrderResponseDTO;
import com.sales.crm.model.Order;
import com.sales.crm.model.OrderObservation;
import com.sales.crm.model.OrderStatus;
import com.sales.crm.model.Quotation;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMapper { //map from quotation to order and map from order to orderDTO

    public Order mapFromQuotationToOrder(Quotation quotation){
        Order order = new Order();
        order.setName(quotation.getClient().getName()+"_"+ quotation.getName());
        order.setOrderStatus(OrderStatus.CONTRACT);
        order.setClient(quotation.getClient());
        order.getUsers().add(quotation.getUser());
        order.setClient(quotation.getClient());

        order.getQuotations().add(quotation);
        quotation.setOrder(order);
        return order;
    }

    public OrderObsResponseDTO mapFromOrderObsToResponse (OrderObservation orderObs){
        OrderObsResponseDTO orderObsResponseDTO = new OrderObsResponseDTO();
        orderObsResponseDTO.setText(orderObs.getText());
        orderObsResponseDTO.setCreatedAt(orderObs.getCreatedAt());
        return orderObsResponseDTO;
    }
    public OrderResponseDTO mapFromOrderToResponseDTO (Order order){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setName(order.getName());
        orderResponseDTO.setOrderStatus(order.getOrderStatus());
        orderResponseDTO.setUserNames(order.getUsers().stream()
                .map(user->user.getName())
                .collect(Collectors.toList()));
        orderResponseDTO.setOrderObsResponseDTOS(order.getOrderObservations().stream()
                .map(orderObs->mapFromOrderObsToResponse(orderObs))
                .collect(Collectors.toList()));
        return orderResponseDTO;
    }


}
