package com.ait.ordenConductor.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ait.ordenConductor.dto.OrderAsignRequestDTO;
import com.ait.ordenConductor.dto.OrderRequestDTO;
import com.ait.ordenConductor.dto.OrderResponseDTO;
import com.ait.ordenConductor.dto.OrderStatusRequestDTO;
import com.ait.ordenConductor.service.OrderService;

@Service 
public class OrderServiceImpl implements OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO body) {
        return null;
    }

    public OrderResponseDTO changeStatusOrder(String id, OrderStatusRequestDTO body) {
        return null;
    }

    public List<OrderResponseDTO> getOrdersByFilters(String status, String fecha, String origen, String destino) {
        return null;
    }

    public OrderResponseDTO getOrderById(String id) {
        return null;
    }

    public OrderResponseDTO asignOrder(OrderAsignRequestDTO body) {
        return null;
    }
}
