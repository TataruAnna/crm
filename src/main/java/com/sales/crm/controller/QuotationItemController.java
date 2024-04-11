package com.sales.crm.controller;

import com.sales.crm.dtos.QuotationItemRequestDTO;
import com.sales.crm.dtos.QuotationItemResponseDTO;
import com.sales.crm.model.QuotationItem;
import com.sales.crm.service.QuotationItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotationItem")
@Tag(name = "QuotationItem", description = "Endpoints for quotation items ")
public class QuotationItemController {
    private QuotationItemService quotationItemService;
    @Autowired
    public QuotationItemController(QuotationItemService quotationItemService) {
        this.quotationItemService = quotationItemService;
    }

    //TODO de pus in QuotationController
    @PostMapping("/add")
    public ResponseEntity<QuotationItemResponseDTO> addQuotationItemToQuotation(@RequestBody QuotationItemRequestDTO quotationItemRequestDTO){
        return  ResponseEntity.ok(quotationItemService.addQuotationItemToQuotation(quotationItemRequestDTO));
    }
}
