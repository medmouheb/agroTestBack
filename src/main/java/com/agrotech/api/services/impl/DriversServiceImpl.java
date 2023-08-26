package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.DriversRepository;
import com.agrotech.api.dto.DriversDto;
import com.agrotech.api.dto.TimeSlotDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.DriversMapper;
import com.agrotech.api.model.Drivers;
import com.agrotech.api.model.TimeSlot;
import com.agrotech.api.services.DriversService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriversServiceImpl implements DriversService {


    @Autowired
    private DriversRepository driversRepository;

    @Autowired
    private DriversMapper driversMapper;


    public Drivers save(Drivers entity) {


        return driversRepository.save(entity);
    }


    @Override
    public DriversDto create(DriversDto dto) {
        Drivers entity = driversMapper.toEntity(dto);
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < dto.getListeDesJours().size(); i++) {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setListeDesJours(dto.getListeDesJours().get(i));
            timeSlot.setHeureDebut(dto.getHeureDebut().get(i));
            timeSlot.setHeureFin(dto.getHeureFin().get(i));
            timeSlots.add(timeSlot);
        }
        entity.setWorkingtime(timeSlots);
        entity = save(entity);
        return driversMapper.toDto(entity);
    }

    @Override
    public DriversDto update(String id, DriversDto dto) throws NotFoundException {
        Optional<Drivers> camOptional =  driversRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Drivers not found ");
        }

        Drivers campanyExisting = camOptional.get();
        driversMapper.partialUpdate(campanyExisting, dto);

        return driversMapper.toDto(save(campanyExisting));
    }
    @Override
    public DriversDto findById(String id) throws NotFoundException {
        Optional<Drivers> campOptional = driversRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Drivers not found ");
        }
        return driversMapper.toDto(campOptional.get());
    }

    @Override
    public List<DriversDto> findAll() {
        return driversRepository.findAll().stream()
                .map(driversMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DriversDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<DriversDto> result = driversRepository.findByNomDuChauffeurContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(driversMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!driversRepository.existsById(id)) {
            throw new NotFoundException("Drivers not found ");
        }
        driversRepository.deleteById(id);
    }

    @Override
    public DriversDto findByCodeEmploye(String codeEmploye) throws NotFoundException {
        Optional<Drivers> campOptional = driversRepository.findByCodeEmploye(codeEmploye);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Drivers not found ");
        }
        return driversMapper.toDto(campOptional.get());
    }


    @Override
    public Page<DriversDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("NomDuVehicles").ascending());
        List<DriversDto> result =  driversRepository.findByIsDeletedAndNomDuChauffeurContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(driversMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Drivers> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("NomDuVehicles").ascending());
        Page<Drivers> result =  driversRepository.findByIsDeletedAndNomDuChauffeurContainingIgnoreCase(false, filter,pageable);

        return result;

    }

    @Override
    public Page<Drivers> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("NomDuVehicles").ascending());
        Page<Drivers> result =  driversRepository.findByIsDeletedAndNomDuChauffeurContainingIgnoreCase(true, filter,pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Drivers> groOptional =  driversRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Drivers not found ");
        }
        Drivers groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        driversRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Drivers> groOptional =  driversRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Drivers not found ");
        }
        Drivers groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        driversRepository.save(groExisting);
    }

    @Override
    public Drivers findByNomDuChauffeur(String nomDuChauffeur) throws NotFoundException {
        return driversRepository.findByNomDuChauffeur(nomDuChauffeur);    }

    @Override
    public Page<DriversDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<DriversDto>  result = driversRepository.findByIsDeletedAndNomDuChauffeurContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(driversMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<DriversDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<DriversDto>  result = driversRepository.findByNomDuChauffeurContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(driversMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
