package com.agrotech.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.agrotech.api.Repository.ChargeRepository;
import com.agrotech.api.dto.ChargeDto;

import com.agrotech.api.mapper.ChargeMapper;
import com.agrotech.api.mapper.CommandeMapper;
import com.agrotech.api.model.Charge;
import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.services.ChargeService;
import lombok.RequiredArgsConstructor;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.agrotech.api.exceptions.NotFoundException;


@Service
@RequiredArgsConstructor
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private ChargeMapper chargeMapper;

    private Charge save(Charge entity) {
        return chargeRepository.save(entity);
    }

    @Override
    public ChargeDto create(ChargeDto dto) {

        return chargeMapper.toDto(
                save(
                        chargeMapper.toEntity(dto)
                )
        );
    }



    @Override
    public ChargeDto update(String id, ChargeDto dto) throws NotFoundException {
        Optional<Charge> optional = chargeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Commande not found");
        }

        Charge existing = optional.get();
        chargeMapper.partialUpdate(existing, dto);
        return chargeMapper.toDto(
                save(existing)
        );
    }

    @Override
    public ChargeDto findById(String id) throws NotFoundException {
        Optional<Charge> optional = chargeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Commande not found");
        }
        return chargeMapper.toDto(optional.get());
    }

    @Override
    public List<ChargeDto> findAll() {
        return chargeRepository.findAll()
                .stream()
                .map(chargeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Charge> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Charge>  result =  chargeRepository.findBySuppNameContainingIgnoreCaseAndIsDeleted(filter,false,pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<ChargeDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<ChargeDto>  result = chargeRepository.findBySuppNameContainingIgnoreCase(filter, pageable)
                .stream()
                //.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(chargeMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public Charge findByname(String name) throws NotFoundException {
        return chargeRepository.findBySuppName(name);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Charge> groOptional =  chargeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found ");
        }
        Charge groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        chargeRepository.save(groExisting);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!chargeRepository.existsById(id)){
            throw new NotFoundException("Fournisseur not found");
        }
        chargeRepository.deleteById(id);
    }

    @Override
    public void importCSV(List<CSVRecord> records) {

    }

    @Override
    public ChargeDto findByCode(String paymentTermCode) throws NotFoundException {
        Optional<Charge> optional = chargeRepository.findBySuppNo(paymentTermCode);
        if (optional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found");
        }
        return chargeMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Charge> groOptional =  chargeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found ");
        }
        Charge groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        chargeRepository.save(groExisting);
    }


    @Override
    public Page<Charge> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Charge>  result =  chargeRepository.findBySuppNameContainingIgnoreCaseAndIsDeleted(filter,true,pageable);
        return result;
    }

    @Override
    public Page<ChargeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        return null;
    }



}
