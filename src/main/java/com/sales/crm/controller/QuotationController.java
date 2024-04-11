package com.sales.crm.controller;

import com.sales.crm.dtos.QuotationRequestDTO;
import com.sales.crm.dtos.QuotationResponseDTO;
import com.sales.crm.service.QuotationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/quotation")
@Tag(name = "Quotation", description = "Endpoints for quotations ")
public class QuotationController {

    private QuotationService quotationService;
    @Autowired
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping("/add")
    public ResponseEntity<QuotationResponseDTO> addQuotationToUser(@RequestBody QuotationRequestDTO quotationRequestDTO){
        try {
            return ResponseEntity.ok(quotationService.addQuotationToUser(quotationRequestDTO));
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }
    @GetMapping("/all") //DE TESTAT
    public ResponseEntity<List<QuotationResponseDTO>> getAllQuotationsByClient(@RequestParam String name){
        return ResponseEntity.ok(quotationService.getAllQuotations(name));

    }


}
