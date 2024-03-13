package com.sales.crm.controller;

import com.sales.crm.dtos.QuotationRequestDTO;
import com.sales.crm.model.Quotation;
import com.sales.crm.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class QuotationController {

    private QuotationService quotationService;
    @Autowired
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @PostMapping("/quotation")
    private ResponseEntity<Quotation> addQuotationToUser(@RequestBody QuotationRequestDTO quotationRequestDTO){
        return ResponseEntity.ok(quotationService.addQuotationToUser(quotationRequestDTO));
    }


}
