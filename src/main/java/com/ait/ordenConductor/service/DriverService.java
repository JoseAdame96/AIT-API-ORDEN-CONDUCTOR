package com.ait.ordenConductor.service;

import java.util.List;

import com.ait.ordenConductor.dto.DriverRequestDTO;
import com.ait.ordenConductor.dto.DriverResponseDTO;
import com.ait.ordenConductor.dto.DriverStatusRequestDTO;

public interface DriverService {
    
    public DriverResponseDTO createDriver(DriverRequestDTO body);
    public List<DriverResponseDTO> getDrivers();
    public DriverResponseDTO updateDriver(String id, DriverStatusRequestDTO body);
}
