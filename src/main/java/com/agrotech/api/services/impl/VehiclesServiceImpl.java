package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VehiclesRepository;
import com.agrotech.api.dto.VehiclesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VehiclesMapper;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.Vehicles;
import com.agrotech.api.services.VehiclesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiclesServiceImpl implements VehiclesService {


    @Autowired
    private VehiclesRepository vehiclesRepository;
    @Autowired
    private VehiclesMapper vehiclesMapper;

    public Vehicles save(Vehicles dto) {
        return vehiclesRepository.save(dto);
    }


    @Override
    public VehiclesDto create(VehiclesDto dto) {
        return vehiclesMapper.toDto(vehiclesRepository.save(vehiclesMapper.toEntity(dto)));
    }

    @Override
    public VehiclesDto update(String id, VehiclesDto dto) throws NotFoundException {
        Optional<Vehicles> camOptional =  vehiclesRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Vehicles not found ");
        }

        Vehicles campanyExisting = camOptional.get();
        vehiclesMapper.partialUpdate(campanyExisting, dto);

        return vehiclesMapper.toDto(save(campanyExisting));    }

    @Override
    public VehiclesDto findById(String id) throws NotFoundException {
        Optional<Vehicles> campOptional = vehiclesRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Vehicles not found ");
        }
        return vehiclesMapper.toDto(campOptional.get());    }

    @Override
    public List<VehiclesDto> findAll() {
        return vehiclesRepository.findAll().stream()
                .map(vehiclesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VehiclesDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VehiclesDto>  result =  vehiclesRepository.findByNomDuVehiculeContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vehiclesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vehiclesRepository.existsById(id)) {
            throw new NotFoundException("Vehicles not found ");
        }
        vehiclesRepository.deleteById(id);
    }


    @Override
    public VehiclesDto findByCodeVehicle(String codeVehicule) throws NotFoundException {
        Optional<Vehicles> campOptional = vehiclesRepository.findByCodeVehicule(codeVehicule);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Vehicles not found ");
        }
        return vehiclesMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VehiclesDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuVehicule").ascending());
        List<VehiclesDto> result =  vehiclesRepository.findByIsDeletedAndNomDuVehiculeContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vehiclesMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Vehicles> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuVehicule").ascending());
        Page<Vehicles> result =  vehiclesRepository.findByIsDeletedAndNomDuVehiculeContainingIgnoreCase(false, filter,pageable);

        return result;    }

    @Override
    public Page<Vehicles> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuVehicule").ascending());
        Page<Vehicles> result =  vehiclesRepository.findByIsDeletedAndNomDuVehiculeContainingIgnoreCase(true, filter,pageable);
        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Vehicles> groOptional =  vehiclesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Vehicles not found ");
        }
        Vehicles groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vehiclesRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Vehicles> groOptional =  vehiclesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Vehicles not found ");
        }
        Vehicles groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vehiclesRepository.save(groExisting);
    }

    @Override
    public Vehicles findByNomDuVehicule(String nomDuVehicule) throws NotFoundException {
        return vehiclesRepository.findByNomDuVehicule(nomDuVehicule);    }

    @Override
    public Page<VehiclesDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuVehicule").ascending());
        List<VehiclesDto>  result = vehiclesRepository.findByIsDeletedAndNomDuVehiculeContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vehiclesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);     }

    @Override
    public Page<VehiclesDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VehiclesDto>  result = vehiclesRepository.findByNomDuVehiculeContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vehiclesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }


}
