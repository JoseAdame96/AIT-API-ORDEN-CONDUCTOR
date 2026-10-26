package com.ait.ordenConductor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.ordenConductor.entity.DriverEntity;

public interface DriverRepository extends JpaRepository<DriverEntity, String> {
    
    List<DriverEntity> findByActive(Boolean active);
}
