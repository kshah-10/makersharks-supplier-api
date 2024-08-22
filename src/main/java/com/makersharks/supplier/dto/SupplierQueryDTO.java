package com.makersharks.supplier.dto;

import com.makersharks.supplier.model.Supplier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SupplierQueryDTO {
    @NotNull
    private String location;

    @NotNull
    private Supplier.NatureOfBusiness natureOfBusiness;

    @NotNull
    private Supplier.ManufacturingProcess manufacturingProcess;
}