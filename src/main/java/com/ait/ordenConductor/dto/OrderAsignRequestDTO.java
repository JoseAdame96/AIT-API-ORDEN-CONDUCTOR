package com.ait.ordenConductor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class OrderAsignRequestDTO {

    @NotNull 
    @NotEmpty 
    private String idOrder;

    @NotNull 
    @NotEmpty
    private String idDriver;
}
