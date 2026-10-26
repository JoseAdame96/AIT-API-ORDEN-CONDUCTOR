package com.ait.ordenConductor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DriverRequestDTO {
    
    @NotNull 
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String licenseNumber;
}
