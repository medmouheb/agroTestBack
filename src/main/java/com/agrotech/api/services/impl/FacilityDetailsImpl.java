package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.FacilityDetailsRepository;

import com.agrotech.api.dto.FacilityDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FacilityDetailsMapper;

import com.agrotech.api.model.FacilityDetails;
import com.agrotech.api.services.FacilityDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacilityDetailsImpl implements FacilityDetailsService {

    @Autowired
    private FacilityDetailsMapper facilityDetailsMapper;

    @Autowired
    private FacilityDetailsRepository facilityDetailsRepository;


    public FacilityDetails save(FacilityDetails dto) {
        System.out.println(dto.getFacilityID());
        System.out.println(dto.getFacilityName());
        System.out.println(dto.getPrimary());

        return facilityDetailsRepository.save(dto);
    }
    @Override
    public FacilityDetailsDto create(FacilityDetailsDto dto) {
        return facilityDetailsMapper.toDto(save(facilityDetailsMapper.toEntity(dto)));

    }

    public FacilityDetails create1(FacilityDetails dto) {
        return facilityDetailsRepository.save(dto);

    }

    @Override
    public FacilityDetailsDto update(String s, FacilityDetailsDto dto) throws NotFoundException {
        Optional<FacilityDetails> faciOptional =  facilityDetailsRepository.findById(s);
        if(faciOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        FacilityDetails facilityDetailsExisting = faciOptional.get();
        facilityDetailsMapper.partialUpdate(facilityDetailsExisting, dto);

        return facilityDetailsMapper.toDto(save(facilityDetailsExisting));
    }

    @Override
    public FacilityDetailsDto findById(String s) throws NotFoundException {
        Optional<FacilityDetails> faciOptional = facilityDetailsRepository.findById(s);
        if(faciOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return facilityDetailsMapper.toDto(faciOptional.get());
    }

    @Override
    public List<FacilityDetailsDto> findAll() {
        return facilityDetailsRepository.findAll().stream()
                .map(facilityDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FacilityDetailsDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<FacilityDetailsDto>  result = facilityDetailsRepository.findByFacilityNameContainingIgnoreCase(filter, pageable)
                .stream()
                .map(facilityDetailsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);

    }

    @Override
    public void delete(String s) throws NotFoundException {
        if(!facilityDetailsRepository.existsById(s)) {
            throw new NotFoundException("facilitydetails not found ");
        }

        facilityDetailsRepository.deleteById(s);
    }

    @Override
    public FacilityDetailsDto findByFacilityID(String facilityID) throws NotFoundException {
        Optional<FacilityDetails> faciOptional = facilityDetailsRepository.findById(facilityID);
        if(faciOptional.isEmpty()) {
            throw new NotFoundException("facilitydetails not found ");
        }
        return facilityDetailsMapper.toDto(faciOptional.get());
    }

    @Override
    public Page<FacilityDetailsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("facilityName").ascending());
        List<FacilityDetailsDto> result =  facilityDetailsRepository.findByIsDeletedAndFacilityNameContainingIgnoreCase(false,filter, pageable)
                .stream()
                .map(facilityDetailsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<FacilityDetails> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("facilityName").ascending());
        Page<FacilityDetails> result =  facilityDetailsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<FacilityDetails> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("facilityName").ascending());
        Page<FacilityDetails> result =  facilityDetailsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<FacilityDetails> groOptional =  facilityDetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("facilitydetails not found ");
        }
        FacilityDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        facilityDetailsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<FacilityDetails> groOptional =  facilityDetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("facilitydetails not found ");
        }
        FacilityDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        facilityDetailsRepository.save(groExisting);
    }

    @Override
    public FacilityDetails findByFacilityName(String facilityName) throws NotFoundException {
        return null;
    }

    @Override
    public Page<FacilityDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("facilityName").ascending());
        List<FacilityDetailsDto> result =  facilityDetailsRepository.findByIsDeletedAndFacilityNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                .map(facilityDetailsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public Page<FacilityDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<FacilityDetailsDto>  result = facilityDetailsRepository.findByFacilityNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(facilityDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
