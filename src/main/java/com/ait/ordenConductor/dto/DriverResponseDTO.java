package com.ait.ordenConductor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DriverResponseDTO {

    private String id;
    private String name;
    private String licenseNumber;
    private Boolean active;
    private String createdAt;
    private String updatedAt;
}
