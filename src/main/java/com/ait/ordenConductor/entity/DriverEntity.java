package com.ait.ordenConductor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DriverEntity {
    
    @Id 
    private String id;

    private String name;
    private String licenseNumber;
    private Boolean active;
    private String createdAt;
    private String updatedAt;
}
