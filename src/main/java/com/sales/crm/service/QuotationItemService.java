package com.sales.crm.service;

import com.sales.crm.dtos.QuotationItemRequestDTO;
import com.sales.crm.dtos.QuotationItemResponseDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Product;
import com.sales.crm.model.Quotation;
import com.sales.crm.model.QuotationItem;
import com.sales.crm.model.User;
import com.sales.crm.repository.ProductRepository;
import com.sales.crm.repository.QuotationRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class QuotationItemService {
    QuotationRepository quotationRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    @Autowired
    public QuotationItemService(QuotationRepository quotationRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.quotationRepository = quotationRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public QuotationItemResponseDTO addQuotationItemToQuotation(QuotationItemRequestDTO quotationItemRequestDTO){
        //gasesc quotation-ul dupa id
        //gasesc userul dupa id-ul din order
        //creez o un quotationItem nou, il setez cu datele din request,
        // adaug item-ul la lista din order

        Long quotationId = quotationItemRequestDTO.getQuotationId();
        Quotation quotation = quotationRepository.findById(quotationId).orElseThrow(()->new ResourceNotFoundException("quotation does not exist, please make one or check the id"));
        Long userId = quotation.getUser().getId();
        User user = userRepository.findUserById(userId).orElseThrow(()->new ResourceNotFoundException("user does not exist, please check the quotation"));
        Long productId = quotationItemRequestDTO.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("product does not exist"));

        QuotationItem quotationItem = mapFromDTOToQuotationItem(quotationItemRequestDTO,quotation,product);

        quotation.getQuotationItems().add(quotationItem);

         quotationRepository.save(quotation);
         return mapFromQuotationItemToResponseRTO(quotationItem);
    }
    @Transactional
    public QuotationItem mapFromDTOToQuotationItem(QuotationItemRequestDTO quotationItemRequestDTO, Quotation quotation, Product product){
        QuotationItem quotationItem = new QuotationItem();
        quotationItem.setQuantity(quotationItemRequestDTO.getQuantity());
        quotationItem.setQuotation(quotation);
        quotationItem.setProduct(product);
        quotationItem.setPrice( product.getBuyingPrice());
        return quotationItem;
    }

    public QuotationItemResponseDTO mapFromQuotationItemToResponseRTO (QuotationItem quotationItem){
        QuotationItemResponseDTO quotationItemResponseDTO= new QuotationItemResponseDTO();
        quotationItemResponseDTO.setProductName(quotationItem.getProduct().getProductCode());
        quotationItemResponseDTO.setPrice(quotationItem.getPrice());
        quotationItemResponseDTO.setQuantity(quotationItem.getQuantity());
        quotationItemResponseDTO.setTotalPrice(quotationItem.getPrice()*quotationItem.getQuantity());
        return quotationItemResponseDTO;

    }

}
