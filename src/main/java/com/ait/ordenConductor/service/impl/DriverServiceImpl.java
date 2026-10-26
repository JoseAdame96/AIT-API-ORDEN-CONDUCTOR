package com.ait.ordenConductor.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.ordenConductor.dto.DriverRequestDTO;
import com.ait.ordenConductor.dto.DriverResponseDTO;
import com.ait.ordenConductor.dto.DriverStatusRequestDTO;
import com.ait.ordenConductor.entity.DriverEntity;
import com.ait.ordenConductor.exception.ExcepcionGenerica;
import com.ait.ordenConductor.repository.DriverRepository;
import com.ait.ordenConductor.service.DriverService;

@Service 
public class DriverServiceImpl implements DriverService {

    private DriverRepository repository;

    public DriverServiceImpl(DriverRepository repository) {
        this.repository = repository;
    }

    public DriverResponseDTO createDriver(DriverRequestDTO body) {
        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setName(body.getName());
        driverEntity.setLicenseNumber(body.getLicenseNumber());
        driverEntity.setActive(true);
        driverEntity.setCreatedAt(LocalDateTime.now());
        driverEntity.setUpdatedAt(LocalDateTime.now());

        repository.save(driverEntity);

        return this.driverEntityToDriverResponseDTO(driverEntity);
    }

    public List<DriverResponseDTO> getDrivers() {

        List<DriverEntity> listDriverEntity = null;
        listDriverEntity = repository.findByActive(true);

        return this.listDriverEntityToListDriverResponseDTO(listDriverEntity);
    }

    public DriverResponseDTO updateDriver(String id, DriverStatusRequestDTO body) {
        Optional<DriverEntity> entityOptional = repository.findById(id);

        if(entityOptional.isEmpty()) {
            throw new ExcepcionGenerica("No se encontró el ID de conductor" + id, new ArrayList<>() , HttpStatus.NOT_FOUND);
        }

        DriverEntity entity = entityOptional.get();
        entity.setActive(body.getActive());

        repository.save(entity);

        return this.driverEntityToDriverResponseDTO(entity);
    }


    private DriverResponseDTO driverEntityToDriverResponseDTO(DriverEntity entity) {
        DriverResponseDTO responseDTO = new DriverResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setName(entity.getName());
        responseDTO.setLicenseNumber(entity.getLicenseNumber());
        responseDTO.setActive(entity.getActive());
        responseDTO.setCreatedAt(entity.getCreatedAt().toString());
        responseDTO.setUpdatedAt(entity.getUpdatedAt().toString());
        return responseDTO;
    }

    private List<DriverResponseDTO> listDriverEntityToListDriverResponseDTO(List<DriverEntity> listEntity) {
        List<DriverResponseDTO> response = new ArrayList<>();
        listEntity.forEach(f -> {
            response.add(driverEntityToDriverResponseDTO(f));
        });
        return response;
    }
}
