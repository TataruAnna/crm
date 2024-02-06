package com.sales.crm.controller;

import com.sales.crm.dtos.ProductRequestDTO;
import com.sales.crm.model.Product;
import com.sales.crm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct (@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.addProduct(productRequestDTO));
    }
}
