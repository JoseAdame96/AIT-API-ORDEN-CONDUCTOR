package com.ait.ordenConductor.entity;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    
    public static Specification<OrderEntity> byStatus(String status) {
        return (root, query, cb) -> status == null ? null : cb.like(root.get("status"), "%" + status + "%");
    }

    public static Specification<OrderEntity> byDateTime(LocalDateTime dateTime) {
        return (root, query, cb) -> dateTime == null ? null : cb.like(root.get("createdAt"), "%" + dateTime + "%");
    }

    public static Specification<OrderEntity> byOrigin(String origin) {
        return (root, query, cb) -> origin == null ? null : cb.like(root.get("origin"), "%" + origin + "%");
    }

    public static Specification<OrderEntity> byDestination(String destination) {
        return (root, query, cb) -> destination == null ? null : cb.like(root.get("destination"), "%" + destination + "%");
    }
}
