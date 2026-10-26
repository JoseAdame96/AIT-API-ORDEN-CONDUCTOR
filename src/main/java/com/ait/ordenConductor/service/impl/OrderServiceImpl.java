package com.ait.ordenConductor.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.ordenConductor.dto.OrderAsignRequestDTO;
import com.ait.ordenConductor.dto.OrderRequestDTO;
import com.ait.ordenConductor.dto.OrderResponseDTO;
import com.ait.ordenConductor.dto.OrderStatusRequestDTO;
import com.ait.ordenConductor.entity.DriverEntity;
import com.ait.ordenConductor.entity.OrderEntity;
import com.ait.ordenConductor.entity.OrderSpecification;
import com.ait.ordenConductor.enums.OrderStatusEnum;
import com.ait.ordenConductor.exception.ExcepcionGenerica;
import com.ait.ordenConductor.repository.DriverRepository;
import com.ait.ordenConductor.repository.OrderRepository;
import com.ait.ordenConductor.service.OrderService;

@Service 
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private DriverRepository driverRepository;

    public OrderServiceImpl(OrderRepository orderRepository, DriverRepository driverRepository) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO body) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setStatus(OrderStatusEnum.CREATED.toString());
        orderEntity.setOrigin(body.getOrigin());
        orderEntity.setDestination(body.getDestination());
        orderEntity.setDriver(null);

        orderEntity.setCreatedAt(LocalDateTime.now());
        orderEntity.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(orderEntity);

        return this.orderEntityToOrderResponseDTO(orderEntity);
    }

    public OrderResponseDTO changeStatusOrder(String id, OrderStatusRequestDTO body) {

        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);
        if (optionalOrderEntity.isEmpty()) {
            throw new ExcepcionGenerica("No se encontró el ID de orden" + id, new ArrayList<>() , HttpStatus.NOT_FOUND);
        }

        OrderEntity orderEntity = optionalOrderEntity.get();

        body.setStatus(body.getStatus().trim().toUpperCase());
        
        switch (orderEntity.getStatus().toString()) {
            case "CREATED" -> {
                if(body.getStatus().equals(OrderStatusEnum.IN_TRANSIT.toString())
                    && orderEntity.getDriver() == null) {
                    throw new ExcepcionGenerica("No hay conductor asignado", new ArrayList<>() , HttpStatus.BAD_REQUEST);
                }

                if(body.getStatus().equals(OrderStatusEnum.DELIVERED.toString())) {
                    throw new ExcepcionGenerica("No se puede asignar este estado", new ArrayList<>() , HttpStatus.BAD_REQUEST);
                }
                body.getStatus();
            }
            case "IN_TRANSIT" -> {
                if(body.getStatus().equals(OrderStatusEnum.CREATED.toString())) {
                    throw new ExcepcionGenerica("No se puede asignar este estado", new ArrayList<>() , HttpStatus.BAD_REQUEST);
                }
            }
            case "DELIVERED" -> throw new ExcepcionGenerica("No se puede asignar este estado", List.of("Estado final: no se puede reasignar") , HttpStatus.BAD_REQUEST);
            case "CANCELLED" -> throw new ExcepcionGenerica("No se puede asignar este estado", List.of("Esta orden ya está cancelada") , HttpStatus.BAD_REQUEST);

            default -> throw new ExcepcionGenerica("No se puede asignar este estado", List.of("Estado inválido") , HttpStatus.BAD_REQUEST);
        }

        orderEntity.setStatus(body.getStatus());
        orderRepository.save(orderEntity);

        return this.orderEntityToOrderResponseDTO(orderEntity);
    }

    public List<OrderResponseDTO> getOrdersByFilters(String status, String fecha, String origen, String destino) {

        Specification<OrderEntity> orderSpec = Specification.allOf(
            OrderSpecification.byStatus(status)
            .and(OrderSpecification.byDateTime(null))
            .and(OrderSpecification.byOrigin(origen))
            .and(OrderSpecification.byDestination(destino))
        );

        List<OrderEntity> orderList = orderRepository.findAll(orderSpec);
        return this.listOrderEntityToListOrderResponseDTO(orderList);
    }

    public OrderResponseDTO getOrderById(String id) {
        
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);

        if(optionalOrderEntity.isEmpty()) {
            throw new ExcepcionGenerica("No se encontró el ID de orden" + id, new ArrayList<>() , HttpStatus.NOT_FOUND);
        }

        return this.orderEntityToOrderResponseDTO(optionalOrderEntity.get());
    }

    public OrderResponseDTO asignOrder(OrderAsignRequestDTO body) {

        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(body.getIdOrder());

        if(optionalOrderEntity.isEmpty()) {
            throw new ExcepcionGenerica("No se encontró el ID de orden" + body.getIdOrder(), new ArrayList<>() , HttpStatus.NOT_FOUND);
        }

        OrderEntity orderEntity = optionalOrderEntity.get();
        if (!orderEntity.getStatus().equals(OrderStatusEnum.CREATED.toString())) {
            throw new ExcepcionGenerica("No se puede reasignar un conductor", List.of("La orden no se encuentra en estado creado") , HttpStatus.BAD_REQUEST);
        }

        Optional<DriverEntity> optionalDriverEntity = driverRepository.findById(body.getIdDriver());

        if(optionalDriverEntity.isEmpty()) {
            throw new ExcepcionGenerica("No se encontró el ID de conductor" + body.getIdDriver(), new ArrayList<>() , HttpStatus.NOT_FOUND);
        }

        orderEntity.setDriver(optionalDriverEntity.get());
        orderRepository.save(orderEntity);

        return this.orderEntityToOrderResponseDTO(orderEntity);
    }

    private OrderResponseDTO orderEntityToOrderResponseDTO(OrderEntity entity) {
        OrderResponseDTO response = new OrderResponseDTO();

        response.setId(entity.getId());
        response.setStatus(entity.getStatus());
        response.setOrigin(entity.getOrigin());
        response.setDestination(entity.getDestination());
        response.setIdDriver((entity.getDriver()==null) ? null : entity.getDriver().getId());
        response.setCreatedAt(entity.getCreatedAt().toString());
        response.setUpdatedAt(entity.getUpdatedAt().toString());

        return response;
    }

    private List<OrderResponseDTO> listOrderEntityToListOrderResponseDTO(List<OrderEntity> listEntity) {
        List<OrderResponseDTO> response = new ArrayList<>();
        listEntity.forEach(f -> {
            response.add(orderEntityToOrderResponseDTO(f));
        });
        return response;
    }
}
