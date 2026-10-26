package com.ait.ordenConductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.ordenConductor.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    
}
