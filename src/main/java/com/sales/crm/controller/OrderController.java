package com.sales.crm.controller;

import com.sales.crm.dtos.OrderResponseDTO;
import com.sales.crm.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Tag(name = "Order", description = "Endpoints for orders")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{quotationId}")
    public ResponseEntity<OrderResponseDTO> addQuotationToOrder(@PathVariable Long quotationId){
        return ResponseEntity.ok(orderService.addQuotationToOrder(quotationId));
    }

    @PostMapping("/addUser/{orderId}/{userId}")
        public ResponseEntity<OrderResponseDTO> addUserToOrder(@PathVariable Long orderId, @PathVariable  Long userId ){
            return ResponseEntity.ok(orderService.addUserToOrder(orderId, userId));
        }
    }

