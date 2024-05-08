package com.sales.crm.service;

import com.sales.crm.MapperService.SupplierMapper;
import com.sales.crm.dtos.SupplierDTO;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.model.Supplier;
import com.sales.crm.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;
    private SupplierMapper supplierMapper;
    @Autowired
    public SupplierService(SupplierRepository supplierRepository,SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
        this.supplierRepository = supplierRepository;
    }
    @Transactional
    public SupplierDTO addSupplier(SupplierDTO supplierDTO){

         supplierRepository.save(supplierMapper.mapFromDTOToSupplier(supplierDTO));
         return supplierDTO;
    }
    @Transactional
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO, Long supplierId){
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()->new ResourceNotFoundException("Supplier not found!"));
        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplierRepository.save(supplier);

        return supplierMapper.mapFromSupplierToDTO(supplier);

    }
    @Transactional
    public List<SupplierDTO> getAllSuppliers(){
        List<SupplierDTO> allSuppliers = supplierRepository.findAll().stream()
                .map(supplier -> supplierMapper.mapFromSupplierToDTO(supplier))
                .collect(Collectors.toList());
        return allSuppliers;

    }
    @Transactional
    public List<SupplierDTO> deleteSupplier(Long supplierId){
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()->new ResourceNotFoundException("Supplier not found!"));
        supplierRepository.delete(supplier);
        return getAllSuppliers();

    }



}
