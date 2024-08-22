package com.makersharks.supplier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersharks.supplier.dto.SupplierQueryDTO;
import com.makersharks.supplier.model.Supplier;
import com.makersharks.supplier.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SupplierController.class)
class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testQuerySuppliers() throws Exception {
        // Given
        SupplierQueryDTO queryDTO = new SupplierQueryDTO();
        queryDTO.setLocation("India");
        queryDTO.setNatureOfBusiness(Supplier.NatureOfBusiness.SMALL_SCALE);
        queryDTO.setManufacturingProcess(Supplier.ManufacturingProcess.THREE_D_PRINTING);

        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setCompanyName("Test Company");
        supplier.setLocation("India");
        supplier.setNatureOfBusiness(Supplier.NatureOfBusiness.SMALL_SCALE);
        supplier.setManufacturingProcesses(new HashSet<>(Arrays.asList(Supplier.ManufacturingProcess.THREE_D_PRINTING)));

        Page<Supplier> supplierPage = new PageImpl<>(Arrays.asList(supplier));

        when(supplierService.searchSuppliers(any(), any())).thenReturn(supplierPage);

        // When & Then
        mockMvc.perform(post("/api/supplier/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(queryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].companyName").value("Test Company"))
                .andExpect(jsonPath("$.content[0].location").value("India"))
                .andExpect(jsonPath("$.content[0].natureOfBusiness").value("SMALL_SCALE"))
                .andExpect(jsonPath("$.content[0].manufacturingProcesses[0]").value("THREE_D_PRINTING"));
    }
}