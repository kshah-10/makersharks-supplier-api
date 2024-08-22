package com.makersharks.supplier.controller;

import com.makersharks.supplier.dto.SupplierQueryDTO;
import com.makersharks.supplier.dto.SupplierResponseDTO;
import com.makersharks.supplier.model.Supplier;
import com.makersharks.supplier.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/query")
    public ResponseEntity<Page<SupplierResponseDTO>> querySuppliers(@Valid @RequestBody SupplierQueryDTO queryDTO, Pageable pageable) {
        Page<Supplier> suppliers = supplierService.searchSuppliers(queryDTO, pageable);
        Page<SupplierResponseDTO> responseDTOs = suppliers.map(this::convertToDTO);
        return ResponseEntity.ok(responseDTOs);
    }

    private SupplierResponseDTO convertToDTO(Supplier supplier) {
        return new SupplierResponseDTO(
            supplier.getSupplierId(),
            supplier.getCompanyName(),
            supplier.getWebsite(),
            supplier.getLocation(),
            supplier.getNatureOfBusiness(),
            supplier.getManufacturingProcesses()
        );
    }
}