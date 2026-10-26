package com.ait.ordenConductor.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class DriverStatusRequestDTO {

    @NotNull
    private Boolean active;
}
