package com.ait.ordenConductor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderEntity {
    
    @Id 
    private String id;

    private String status;
    private String origin;
    private String destination;
    private String createdAt;
    private String updatedAt;
}
