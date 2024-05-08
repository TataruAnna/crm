package com.sales.crm.dtos.mapper;


import com.sales.crm.dtos.QuotationResponseDTO;
import com.sales.crm.model.Client;
import com.sales.crm.model.Quotation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class QuotationMapper {
//
//    //        quotationResponseDTO.setClientName(quotation.getClient().getName());
//    //        quotationResponseDTO.setSellingMargin(quotation.getSellingMargin());
//    //        quotationResponseDTO.setTotalPrice(quotation.getTotalPrice());
//    //        quotationResponseDTO.setDateCreated(quotation.getDateCreated());
//    //        quotationResponseDTO.setUserName(quotation.getUser().getName());
//    //        quotationResponseDTO.setClientName(quotation.getClient().getName());
//    @Mapping(target = "clientName", expression = "java(quotation.getClient().getName())")
//    @Mapping(target = "sellingMargin", expression = "java(quotation.getSellingMargin())")
//    @Mapping(target = "totalPrice", expression = "java(quotation.getTotalPrice())")
//    @Mapping(target = "dateCreated", expression = "java(quotation.getDateCreated())")
//    @Mapping(target = "userName", expression = "java(quotation.getUser().getName())")
//    public abstract QuotationResponseDTO mapFromQuotationToResponseDTO(Quotation quotation);
}
