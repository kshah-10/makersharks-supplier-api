package com.makersharks.supplier.dto;

import com.makersharks.supplier.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SupplierResponseDTO {
    private Long supplierId;
    private String companyName;
    private String website;
    private String location;
    private Supplier.NatureOfBusiness natureOfBusiness;
    private Set<Supplier.ManufacturingProcess> manufacturingProcesses;
}