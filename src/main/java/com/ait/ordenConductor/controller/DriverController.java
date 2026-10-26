package com.ait.ordenConductor.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ait.ordenConductor.dto.DriverRequestDTO;
import com.ait.ordenConductor.dto.DriverResponseDTO;
import com.ait.ordenConductor.dto.DriverStatusRequestDTO;
import com.ait.ordenConductor.service.DriverService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/driver")
public class DriverController {

    private DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@RequestBody DriverRequestDTO body) {
        var response = new ResponseEntity<>(service.createDriver(body), CREATED);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<DriverResponseDTO>> getDrivers() {
        var response = new ResponseEntity<>(service.getDrivers(), OK);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable String id, @RequestBody DriverStatusRequestDTO body) {
        var response = new ResponseEntity<>(service.updateDriver(id, body), OK);
        return response;
    }
}
