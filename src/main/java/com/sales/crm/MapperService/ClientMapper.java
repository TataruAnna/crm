package com.sales.crm.MapperService;

import com.sales.crm.dtos.ClientResponseDTO;
import com.sales.crm.dtos.mapper.QuotationMapper;
import com.sales.crm.model.Client;
import com.sales.crm.model.Quotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientMapper {
    private QuotationMapper quotationMapper;
    @Autowired
    public ClientMapper(QuotationMapper quotationMapper) {
        this.quotationMapper = quotationMapper;
    }
//    @Transactional
//    public ClientResponseDTO mapFromClientToResponseDTO(Client client){
//
//        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
//        clientResponseDTO.setName(client.getName());
//        clientResponseDTO.setPhoneNumber(client.getPhoneNumber());
//        clientResponseDTO.setEmail(client.getEmail());
//        clientResponseDTO.setAddress(client.getAddress());
//        clientResponseDTO.setUserId(client.getUser().getId());
//        List<Quotation> clientQuotations = client.getQuotations();
//
//        clientResponseDTO.setQuotationResponseDTOs(clientQuotations.stream()
//                .map(quotation -> quotationMapper.mapFromQuotationToDTOResponse(quotation))
//                .collect(Collectors.toList()));
//
//        return clientResponseDTO;
//    }
}
