package com.ait.ordenConductor.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="drivers")
public class DriverEntity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
