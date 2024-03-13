package com.sales.crm.service;

import com.sales.crm.dtos.QuotationItemRequestDTO;
import com.sales.crm.dtos.QuotationRequestDTO;
import com.sales.crm.dtos.QuotationResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.*;
import com.sales.crm.repository.ClientRepository;
import com.sales.crm.repository.ProductRepository;
import com.sales.crm.repository.QuotationRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class QuotationService {

    private QuotationRepository quotationRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ClientRepository clientRepository;

    @Autowired
    public QuotationService(QuotationRepository quotationRepository, UserRepository userRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.quotationRepository = quotationRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Quotation addQuotationToUser (QuotationRequestDTO quotationRequestDTO){

        //gasesc userul dupa id
        //creez o noua comanda
        //atasez comanda de utilizator
        //salvez orderul
        //salvez user-ul
        User user = userRepository.findUserById(quotationRequestDTO.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found in addQuotation"));
        Client client = clientRepository.findById(quotationRequestDTO.getClientId()).orElseThrow(()->new ResourceNotFoundException("client not found "));
        Quotation quotation = new Quotation();
        quotation.setUser(user);
        quotation.setName(quotationRequestDTO.getName());
        quotation.setSellingMargin(quotationRequestDTO.getSellingMargin());
        quotation.setClient(client);
        quotation.setDateCreated(LocalDateTime.now());

        user.getQuotations().add(quotation);
        return quotationRepository.save(quotation);


    }

    public QuotationResponseDTO mapFromQuotationToDTOResponse(Quotation quotation){
        QuotationResponseDTO quotationResponseDTO = new QuotationResponseDTO();
        quotationResponseDTO.setClientName(quotation.getClient().getName());
        quotationResponseDTO.setSellingMargin(quotation.getSellingMargin());
        quotationResponseDTO.setTotalPrice(quotation.getTotalPrice());
        quotationResponseDTO.setDateCreated(quotation.getDateCreated());
        quotationResponseDTO.setUserName(quotation.getUser().getName());
        quotationResponseDTO.setClientName(quotation.getClient().getName());
        return quotationResponseDTO;


//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        LocalDateTime dateTime = LocalDateTime.parse(quotation.getDateCreated(), dtf);
    }


}
