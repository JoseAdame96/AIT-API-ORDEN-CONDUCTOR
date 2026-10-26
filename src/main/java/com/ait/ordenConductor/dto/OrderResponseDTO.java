package com.ait.ordenConductor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class OrderResponseDTO {

    private String id;
    private String status;
    private String origin;
    private String destination;

    @JsonInclude(Include.NON_NULL)
    private String idDriver;

    private String createdAt;
    private String updatedAt;
}
