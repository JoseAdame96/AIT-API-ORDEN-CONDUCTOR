package com.ait.ordenConductor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class OrderResponseDTO {

    private String id;
    private String status;
    private String origin;
    private String destination;
    private String createdAt;
    private String updatedAt;
}
