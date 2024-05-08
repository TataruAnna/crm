package com.sales.crm.MapperService;

import com.sales.crm.dtos.SupplierDTO;
import com.sales.crm.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SupplierMapper {

    public Supplier mapFromDTOToSupplier(SupplierDTO supplierDTO){
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setProducts(new ArrayList<>());
        return supplier;
    }
    public SupplierDTO mapFromSupplierToDTO(Supplier supplier){
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName(supplier.getName());
        supplierDTO.setAddress(supplier.getAddress());
        return supplierDTO;
    }

}
