package com.sales.crm.controller;

import com.sales.crm.dtos.QuotationItemRequestDTO;
import com.sales.crm.dtos.QuotationItemResponseDTO;
import com.sales.crm.model.QuotationItem;
import com.sales.crm.service.QuotationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotationItemController {
    private QuotationItemService quotationItemService;
    @Autowired
    public QuotationItemController(QuotationItemService quotationItemService) {
        this.quotationItemService = quotationItemService;
    }
    @PostMapping("/qotationItem")
    public ResponseEntity<QuotationItemResponseDTO> addQuotationItemToQuotation(@RequestBody QuotationItemRequestDTO quotationItemRequestDTO){
        return  ResponseEntity.ok(quotationItemService.addQuotationItemToQuotation(quotationItemRequestDTO));
    }
}
