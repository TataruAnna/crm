package com.sales.crm.controller;

import com.sales.crm.dtos.ProductRequestDTO;
import com.sales.crm.model.Product;
import com.sales.crm.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Endpoints for products ")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct (@RequestBody ProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.addProduct(productRequestDTO));
    }
}
