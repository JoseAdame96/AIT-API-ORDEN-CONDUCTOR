package com.ait.ordenConductor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class OrderRequestDTO {

    @NotNull 
    @NotEmpty 
    private String origin;

    @NotNull 
    @NotEmpty
    private String destination;
}
