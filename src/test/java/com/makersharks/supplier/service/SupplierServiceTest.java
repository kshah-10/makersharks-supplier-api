package com.makersharks.supplier.service;

import com.makersharks.supplier.dto.SupplierQueryDTO;
import com.makersharks.supplier.model.Supplier;
import com.makersharks.supplier.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    private SupplierService supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supplierService = new SupplierService(supplierRepository);
    }

    @Test
    void testSearchSuppliers() {
        // Given
        SupplierQueryDTO queryDTO = new SupplierQueryDTO();
        queryDTO.setLocation("India");
        queryDTO.setNatureOfBusiness(Supplier.NatureOfBusiness.SMALL_SCALE);
        queryDTO.setManufacturingProcess(Supplier.ManufacturingProcess.THREE_D_PRINTING);

        Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        supplier1.setCompanyName("Test Company 1");
        supplier1.setLocation("India");
        supplier1.setNatureOfBusiness(Supplier.NatureOfBusiness.SMALL_SCALE);
        supplier1.setManufacturingProcesses(new HashSet<>(Arrays.asList(Supplier.ManufacturingProcess.THREE_D_PRINTING)));

        Page<Supplier> supplierPage = new PageImpl<>(Arrays.asList(supplier1));

        when(supplierRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(supplierPage);

        // When
        Page<Supplier> result = supplierService.searchSuppliers(queryDTO, Pageable.unpaged());

        // Then
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Company 1", result.getContent().get(0).getCompanyName());
    }
}