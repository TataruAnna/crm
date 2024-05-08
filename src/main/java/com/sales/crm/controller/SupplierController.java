package com.sales.crm.controller;

import com.sales.crm.dtos.SupplierDTO;
import com.sales.crm.model.Supplier;
import com.sales.crm.service.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@Tag(name = "Supplier", description = "Endpoints for suplier ")
public class SupplierController {
    private SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/add")
    public ResponseEntity<SupplierDTO> addSupplier(@RequestBody SupplierDTO supplierDTO){
        SupplierDTO newSupplier = supplierService.addSupplier(supplierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSupplier);
    }
    @PostMapping("/update/{supplierId}")
    public ResponseEntity<SupplierDTO> updateSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable Long supplierId){

        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.updateSupplier(supplierDTO, supplierId));

    }
    @GetMapping("/all")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }
    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<List<SupplierDTO>> deleteSupplier(@PathVariable Long supplierId){
        return ResponseEntity.ok(supplierService.deleteSupplier(supplierId));
    }


}
