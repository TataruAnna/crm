package com.sales.crm.MapperService;

import com.sales.crm.dtos.QuotationRequestDTO;
import com.sales.crm.dtos.QuotationResponseDTO;
import com.sales.crm.model.Client;
import com.sales.crm.model.Quotation;
import com.sales.crm.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuotationMapper {
    public Quotation mapFronRequestDTOtoQuotation(QuotationRequestDTO quotationRequestDTO, User user, Client client){
        Quotation quotation = new Quotation();
        quotation.setUser(user);
        quotation.setName(quotationRequestDTO.getName());
        quotation.setSellingMargin(quotationRequestDTO.getSellingMargin());
        quotation.setClient(client);
        quotation.setDateCreated(LocalDateTime.now());
        return  quotation;
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

