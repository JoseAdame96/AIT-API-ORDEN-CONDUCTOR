package com.ait.ordenConductor.service;

import java.util.List;

import com.ait.ordenConductor.dto.OrderAsignRequestDTO;
import com.ait.ordenConductor.dto.OrderRequestDTO;
import com.ait.ordenConductor.dto.OrderResponseDTO;
import com.ait.ordenConductor.dto.OrderStatusRequestDTO;

public interface OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO body);
    public OrderResponseDTO changeStatusOrder(String id, OrderStatusRequestDTO body);
    public List<OrderResponseDTO> getOrdersByFilters(String status, String fecha, String origen, String destino);
    public OrderResponseDTO getOrderById(String id);
    public OrderResponseDTO asignOrder(OrderAsignRequestDTO body);
}
