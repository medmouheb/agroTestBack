package com.agrotech.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.agrotech.api.dto.CommandeDto;
import com.agrotech.api.model.Charge;
import lombok.RequiredArgsConstructor;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotech.api.Repository.CommandeRepository;

import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CommandeMapper;
import com.agrotech.api.model.Commande;
import com.agrotech.api.services.CommandeService;



@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private  CommandeRepository commandeRepository;
    @Autowired
    private  CommandeMapper commandeMapper;

    private Commande save(Commande entity) {
        return commandeRepository.save(entity);
    }

    @Override
    public CommandeDto create(CommandeDto dto) {

        return commandeMapper.toDto(
                save(
                        commandeMapper.toEntity(dto)
                )
        );
    }



    @Override
    public CommandeDto update(String id, CommandeDto dto) throws NotFoundException {
        Optional<Commande> optional = commandeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Commande not found");
        }

        Commande existing = optional.get();
        commandeMapper.partialUpdate(existing, dto);
        return commandeMapper.toDto(
                save(existing)
        );
    }

    @Override
    public CommandeDto findById(String id) throws NotFoundException {
        Optional<Commande> optional = commandeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Commande not found");
        }
        return commandeMapper.toDto(optional.get());
    }

    @Override
    public List<CommandeDto> findAll() {
        return commandeRepository.findAll()
                .stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Commande> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Commande>  result =  commandeRepository.findByPaymentTermNameContainingIgnoreCaseAndIsDeleted(filter,false,pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<CommandeDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<CommandeDto>  result = commandeRepository.findByPaymentTermNameContainingIgnoreCase(filter, pageable)
                .stream()
                //.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public Commande findByname(String name) throws NotFoundException {
        return commandeRepository.findByPaymentTermName(name);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Commande> groOptional =  commandeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found ");
        }
        Commande groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        commandeRepository.save(groExisting);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!commandeRepository.existsById(id)){
            throw new NotFoundException("Fournisseur not found");
        }
        commandeRepository.deleteById(id);
    }

    @Override
    public void importCSV(List<CSVRecord> records) {

    }

    @Override
    public CommandeDto findByCode(String paymentTermCode) throws NotFoundException {
        Optional<Commande> optional = commandeRepository.findByPaymentTermCode(paymentTermCode);
        if (optional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found");
        }
        return commandeMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Commande> groOptional =  commandeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found ");
        }
        Commande groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        commandeRepository.save(groExisting);
    }


    @Override
    public Page<Commande> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Commande>  result =  commandeRepository.findByPaymentTermNameContainingIgnoreCaseAndIsDeleted(filter,true,pageable);
        return result;
    }

    @Override
    public Page<CommandeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        return null;
    }



}
