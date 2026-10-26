package com.ait.ordenConductor.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class ExceptionDTO {
    private String mensaje;
    @JsonInclude(Include.NON_NULL)
    private List<String> detalles;
}
