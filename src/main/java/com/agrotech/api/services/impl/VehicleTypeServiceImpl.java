package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VehicleTypeRepository;
import com.agrotech.api.dto.VehicleTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VehicleTypeMapper;
import com.agrotech.api.model.VehicleType;
import com.agrotech.api.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private final VehicleTypeRepository vehicleTypeRepository;


    @Autowired
    private  final VehicleTypeMapper vehicleTypeMapper;

    public VehicleType save(VehicleType entity) {
        return  vehicleTypeRepository.save(entity);
    }

    @Override
    public VehicleTypeDto create(VehicleTypeDto dto) {

        return vehicleTypeMapper.toDto(save(vehicleTypeMapper.toEntity(dto)));
    }



    @Override
    public VehicleTypeDto update(String s, VehicleTypeDto dto) throws NotFoundException {
        return null;
    }

    @Override
    public VehicleTypeDto findById(String s) throws NotFoundException {
        return null;
    }

    @Override
    public List<VehicleTypeDto> findAll() {
        return null;
    }

    @Override
    public Page<VehicleTypeDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String s) throws NotFoundException {

    }

    @Override
    public VehicleTypeDto findByCode(String code) throws NotFoundException {
        return null;
    }

    @Override
    public Page<VehicleTypeDto> findPage1(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public Page<VehicleType> getpages(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public Page<VehicleType> getpagesarchive(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void archive(String id) throws NotFoundException {

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {

    }

    @Override
    public VehicleType findByname(String name) throws NotFoundException {
        return null;
    }

    @Override
    public Page<VehicleTypeDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public Page<VehicleTypeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        return null;
    }
}
