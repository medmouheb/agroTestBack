package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VehiculeRepository;
import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VehiculeMapper;
import com.agrotech.api.model.Vehicule;
import com.agrotech.api.services.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private VehiculeMapper vehiculeMapper;

    public Vehicule save(Vehicule dto) {

        return vehiculeRepository.save(dto);

    }

    public VehiculeDto create(VehiculeDto dto) {
        return vehiculeMapper.toDto(vehiculeRepository.save(vehiculeMapper.toEntity(dto)));
    }

    public VehiculeDto update(String s, VehiculeDto dto) throws NotFoundException {
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findById(s);

        if (vehiculeOptional.isEmpty()) {
            throw new NotFoundException("vehicule not found ");
        }

        Vehicule vehiculeExisting = vehiculeOptional.get();
        vehiculeMapper.partialUpdate(vehiculeExisting, dto);

        return vehiculeMapper.toDto(save(vehiculeExisting));

    }

    public VehiculeDto findById(String s) throws NotFoundException {
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findById(s);

        if (vehiculeOptional.isEmpty()) {
            throw new NotFoundException("vehicule not found ");
        }

        return vehiculeMapper.toDto(vehiculeOptional.get());
    }

    public List<VehiculeDto> findAll() {
        return vehiculeRepository.findAll().stream()
                .map(vehiculeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<VehiculeDto> findPage(int pageSize, int pageNumber, String filter) {
        return vehiculeRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(vehiculeMapper::toDto);

    }

    public void delete(String s) throws NotFoundException {
        Optional<Vehicule> vehiculeOptional = vehiculeRepository.findById(s);

        if (vehiculeOptional.isEmpty()) {
            throw new NotFoundException("vehicule not found ");
        }

        vehiculeRepository.delete(vehiculeOptional.get());

    }


    public VehiculeDto findByVehiculeCode(String vehiculeCode) throws NotFoundException {
        Optional<Vehicule> campanyOptional =
                vehiculeRepository.findByVehiculeCode(vehiculeCode);

        if (campanyOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        return vehiculeMapper.toDto(campanyOptional.get());
    }

    public Page<VehiculeDto> findPage1(int pageSize, int pageNumber, String filter) {
        return vehiculeRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(vehiculeMapper::toDto);


    }

    public Page<Vehicule> getpages(int pageSize, int pageNumber, String filter) {
        return vehiculeRepository.findByIsDeletedAndVehiculeNameContainingIgnoreCase(false,filter,PageRequest.of(pageNumber, pageSize));
    }

    public Page<Vehicule> getpagesarchive(int pageSize, int pageNumber, String filter) {
        return vehiculeRepository.findAll(PageRequest.of(pageNumber, pageSize));


    }

    public void archive(String id) throws NotFoundException {
        Optional<Vehicule> groOptional = vehiculeRepository.findById(id);
        if (groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Vehicule groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vehiculeRepository.save(groExisting);

    }


    public void setNotArchive(String id) throws NotFoundException {
        Optional<Vehicule> groOptional = vehiculeRepository.findById(id);
        if (groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Vehicule groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vehiculeRepository.save(groExisting);

    }

    public Vehicule findByVehiculeName(String vehiculeName) throws NotFoundException {

        return vehiculeRepository.findByVehiculeName(vehiculeName);


    }

    public Page<VehiculeDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("vehiculeName").ascending());
        List<VehiculeDto> result = vehiculeRepository.findByIsDeletedAndVehiculeCodeContainingIgnoreCase(true, filter, pageable)
                .stream()
                .filter(g -> g.getIsDeleted() != null && g.getIsDeleted())
                .map(vehiculeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    public Page<VehiculeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = (Pageable) PageRequest.of(
                pageNumber, pageSize, Sort.by("vehiculeName").ascending());
        List<VehiculeDto> result = vehiculeRepository.findByIsDeletedAndVehiculeCodeContainingIgnoreCase(true, filter, pageable)
                .stream()
                .filter(g -> g.getIsDeleted() != null && g.getIsDeleted())
                .map(vehiculeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);


    }
}