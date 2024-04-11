package com.sales.crm.service;

import com.sales.crm.MapperService.QuotationMapper;
import com.sales.crm.dtos.QuotationItemRequestDTO;
import com.sales.crm.dtos.QuotationRequestDTO;
import com.sales.crm.dtos.QuotationResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.*;
import com.sales.crm.repository.ClientRepository;
import com.sales.crm.repository.ProductRepository;
import com.sales.crm.repository.QuotationRepository;
import com.sales.crm.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuotationService {

    private QuotationRepository quotationRepository;
    private UserRepository userRepository;
    private UserService userService;
    private ProductRepository productRepository;
    private ClientRepository clientRepository;
    private QuotationMapper quotationMapper;
    private PdfGenerationService pdfGenerationService;
    private EmailService emailService;

    @Autowired
    public QuotationService(QuotationRepository quotationRepository, UserRepository userRepository, ProductRepository productRepository, ClientRepository clientRepository, QuotationMapper quotationMapper,PdfGenerationService pdfGenerationService, EmailService emailService, UserService userService) {
        this.quotationRepository = quotationRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.quotationMapper = quotationMapper;
        this.pdfGenerationService = pdfGenerationService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @Transactional
    public QuotationResponseDTO addQuotationToUser (QuotationRequestDTO quotationRequestDTO) throws MessagingException {

        User user = userService.findLoggedInUser();
        Client client = clientRepository.findById(quotationRequestDTO.getClientId()).orElseThrow(()->new ResourceNotFoundException("client not found "));
        Quotation quotation = quotationMapper.mapFronRequestDTOtoQuotation(quotationRequestDTO,user, client);

        user.getQuotations().add(quotation);
        client.getQuotations().add(quotation);
        quotationRepository.save(quotation);

        pdfGenerationService.generatePdf("Quotation "+ quotation.getName() + " has been generated", "src/main/resources/quotation.pdf");
        emailService.sendMessageWithAttachmentforQuotation(user.getName(),"Quotation generation", "This is an email with an attachment", "src/main/resources/quotation.pdf");

        return quotationMapper.mapFromQuotationToDTOResponse(quotation);

    }

    @Transactional
    public List<QuotationResponseDTO> getAllQuotations(String name){
        Client client = clientRepository.findClientByName(name).orElseThrow(()->new ResourceNotFoundException("client name not found"));
        List<Quotation> allQuotations = client.getQuotations();
        return allQuotations.stream()
                .map(quotation ->quotationMapper.mapFromQuotationToDTOResponse(quotation))
                .collect(Collectors.toList());

    }




}
