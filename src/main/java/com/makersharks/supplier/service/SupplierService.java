package com.makersharks.supplier.service;

import com.makersharks.supplier.dto.SupplierQueryDTO;
import com.makersharks.supplier.model.Supplier;
import com.makersharks.supplier.repository.SupplierRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Page<Supplier> searchSuppliers(SupplierQueryDTO queryDTO, Pageable pageable) {
    Specification<Supplier> spec = Specification.where(null);

    if (queryDTO.getLocation() != null && !queryDTO.getLocation().isEmpty()) {
        String sanitizedLocation = InputSanitizer.sanitize(queryDTO.getLocation());
        spec = spec.and((root, query, cb) -> cb.equal(root.get("location"), sanitizedLocation));
    }

        if (queryDTO.getNatureOfBusiness() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("natureOfBusiness"), queryDTO.getNatureOfBusiness()));
        }

        if (queryDTO.getManufacturingProcess() != null) {
            spec = spec.and((root, query, cb) -> cb.isMember(queryDTO.getManufacturingProcess(), root.get("manufacturingProcesses")));
        }

        return supplierRepository.findAll(spec, pageable);
    }
}