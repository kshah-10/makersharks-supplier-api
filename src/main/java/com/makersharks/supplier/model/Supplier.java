package com.makersharks.supplier.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String companyName;
    private String website;
    private String location;

    @Enumerated(EnumType.STRING)
    private NatureOfBusiness natureOfBusiness;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ManufacturingProcess> manufacturingProcesses;

    public enum NatureOfBusiness {
        SMALL_SCALE, MEDIUM_SCALE, LARGE_SCALE
    }

    public enum ManufacturingProcess {
        MOULDING, THREE_D_PRINTING, CASTING, COATING
    }
}