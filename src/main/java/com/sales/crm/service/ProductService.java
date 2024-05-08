package com.sales.crm.service;

import com.sales.crm.dtos.ProductRequestDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Category;
import com.sales.crm.model.Product;
import com.sales.crm.model.Supplier;
import com.sales.crm.repository.CategoryRepository;
import com.sales.crm.repository.ProductRepository;
import com.sales.crm.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }
    @Transactional
    public Product addProduct ( ProductRequestDTO productRequestDTO){
        Product newProduct = new Product();
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("categoria nu exista"));
        Supplier supplier = supplierRepository.findById(productRequestDTO.getSupplierId()).orElseThrow(()->new ResourceNotFoundException("supplierul nu exista"));

        newProduct.setProductCode(productRequestDTO.getProductCode());
        newProduct.setDescription(productRequestDTO.getDescription());
        newProduct.setBuyingPrice(productRequestDTO.getBuyingPrice());
        newProduct.setSpecialProduct(productRequestDTO.getSpecialProduct());
        newProduct.setStockNumber(productRequestDTO.getStockNumber());
        if(newProduct.getStockNumber()==null){
            newProduct.setStockNumber(1);
        }else {
            newProduct.setStockNumber(newProduct.getStockNumber() + 1);
        }
        newProduct.setCategory(category);
        newProduct.setSupplier(supplier);

        category.getProducts().add(newProduct);
        supplier.getProducts().add(newProduct);

        return productRepository.save(newProduct);

    }
}
