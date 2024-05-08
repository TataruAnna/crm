package com.sales.crm.service;

import com.sales.crm.MapperService.OrderMapper;
import com.sales.crm.MapperService.QuotationMapper;
import com.sales.crm.dtos.OrderResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.exceptions.ResourceNotValidException;
import com.sales.crm.model.Order;
import com.sales.crm.model.Quotation;
import com.sales.crm.model.User;
import com.sales.crm.repository.ClientRepository;
import com.sales.crm.repository.OrderRepository;
import com.sales.crm.repository.QuotationRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    //add quotation to order ->change status of order
    //add user to order ->change status of order
    //add order observations
    //find all orders by client

    //get all order observation for orderId
    //change status of order ->add observation for another user
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private QuotationRepository quotationRepository;
    private UserRepository userRepository;
    private UserService userService;
    private ClientRepository clientRepository;
    private QuotationMapper quotationMapper;
    private PdfGenerationService pdfGenerationService;
    private EmailService emailService;

    public OrderService(OrderRepository orderRepository,OrderMapper orderMapper, QuotationRepository quotationRepository, UserRepository userRepository, UserService userService, ClientRepository clientRepository, QuotationMapper quotationMapper, PdfGenerationService pdfGenerationService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.quotationRepository = quotationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.clientRepository = clientRepository;
        this.quotationMapper = quotationMapper;
        this.pdfGenerationService = pdfGenerationService;
        this.emailService = emailService;
    }

    @Transactional
    //add quotation to order ->change status of order
    public OrderResponseDTO addQuotationToOrder(Long quotationId){
        Quotation quotation = quotationRepository.findById(quotationId).orElseThrow(()->new ResourceNotFoundException("Quotation doest not exist"));
        Order order = orderMapper.mapFromQuotationToOrder(quotation);
        orderRepository.save(order);
        OrderResponseDTO orderResponseDTO = orderMapper.mapFromOrderToResponseDTO(order);
        return orderResponseDTO;
    }
    @Transactional
    public OrderResponseDTO addUserToOrder(Long orderId, Long userId){

        User user = userRepository.findUserById(userId).orElseThrow(()->new ResourceNotFoundException("User not found!"));
        Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found!"));
        order.getUsers().add(user);
        user.getOrders().add(order);
        userRepository.save(user);
        orderRepository.save(order);
        return orderMapper.mapFromOrderToResponseDTO(order);
    }

}
