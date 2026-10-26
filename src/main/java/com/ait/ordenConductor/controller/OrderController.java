package com.ait.ordenConductor.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ait.ordenConductor.dto.OrderAsignRequestDTO;
import com.ait.ordenConductor.dto.OrderRequestDTO;
import com.ait.ordenConductor.dto.OrderResponseDTO;
import com.ait.ordenConductor.dto.OrderStatusRequestDTO;
import com.ait.ordenConductor.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO body) {
        var response = new ResponseEntity<>(service.createOrder(body), CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> changeStatusOrder(@PathVariable String id, @RequestBody OrderStatusRequestDTO body) {
        var response = new ResponseEntity<>(service.changeStatusOrder(id, body), OK);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByFilters(
        @RequestParam(required = false) String status, 
        @RequestParam(required = false) String fecha, 
        @RequestParam(required = false) String origen,
        @RequestParam(required = false) String destino )
    {
        var response = new ResponseEntity<>(service.getOrdersByFilters(status, fecha, origen, destino), OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable  String id) {
        var response = new ResponseEntity<>(service.getOrderById(id), OK);
        return response;
    }

    @PutMapping("/asign")
    public ResponseEntity<OrderResponseDTO> asignOrder(@RequestBody OrderAsignRequestDTO body) {
        var response = new ResponseEntity<>(service.asignOrder(body), OK);
        return response;
    }
}
