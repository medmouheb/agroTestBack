package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VehicleTypeRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VehicleTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VehicleTypeMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VehicleType;
import com.agrotech.api.services.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public VehicleTypeDto update(String id, VehicleTypeDto dto) throws NotFoundException {
        Optional<VehicleType> camOptional =  vehicleTypeRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        VehicleType vehicleTypeExisting = camOptional.get();
        vehicleTypeMapper.partialUpdate(vehicleTypeExisting, dto);

        return vehicleTypeMapper.toDto(save(vehicleTypeExisting));
    }

    @Override
    public VehicleTypeDto findById(String id) throws NotFoundException {
        Optional<VehicleType> campOptional = vehicleTypeRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return vehicleTypeMapper.toDto(campOptional.get());
    }

    @Override
    public List<VehicleTypeDto> findAll() {
        return vehicleTypeRepository.findAll().stream()
                .map(vehicleTypeMapper::toDto)
                .collect(Collectors.toList());
    }



    @Override
    public Page<VehicleTypeDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vehicleTypeRepository.existsById(id)) {
            throw new NotFoundException("Campany not found ");
        }

        vehicleTypeRepository.deleteById(id);


    }

    @Override
    public VehicleTypeDto findByVehicleTypeCode(String vehicleTypeCode) throws NotFoundException {
        Optional<VehicleType> campOptional = vehicleTypeRepository.findByVehicleTypeCode(vehicleTypeCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("vehicleType not found ");
        }
        return vehicleTypeMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VehicleTypeDto> findPage1(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public Page<VehicleType> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("vehicleTypeName").ascending());
        Page<VehicleType> result =  vehicleTypeRepository.findByVehicleTypeNameContainingIgnoreCaseAndIsDeleted(filter,false, pageable);

        return result;
    }

    @Override
    public Page<VehicleType> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("vehicleTypeName").ascending());
        Page<VehicleType> result =  vehicleTypeRepository.findByVehicleTypeNameContainingIgnoreCaseAndIsDeleted(filter,true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VehicleType> groOptional =  vehicleTypeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VehicleType groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vehicleTypeRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VehicleType> groOptional =  vehicleTypeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VehicleType groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vehicleTypeRepository.save(groExisting);
    }

    @Override
    public VehicleType findByVehicleTypeName(String name) throws NotFoundException {
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
