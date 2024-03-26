package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.FactureRepository;
import com.agrotech.api.controller.FileController;
import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FactureMapper;
import com.agrotech.api.model.Facture;
import com.agrotech.api.services.FactureService;
import com.agrotech.api.services.ProductionService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    @Autowired
    private FactureRepository factureRepository ;

    @Autowired
    private FactureMapper factureMapper ;

    @Autowired
    private FileController fileController;

    public Facture save(Facture dto) {

        return factureRepository.save(dto);

    }


    @Override
    public FactureDto create(FactureDto dto) throws DocumentException, FileNotFoundException {
        dto.setLastUpdate(LocalDateTime.now());
        if(dto.isSendMail()){
            String t=fileController.getatest1(dto);
            dto.setFile(t);
        }

        return factureMapper.toDto(save(factureMapper.toEntity(dto)));

    }



    @Override
    public FactureDto update(String id, FactureDto dto) throws NotFoundException {
        dto.setLastUpdate(LocalDateTime.now());

        Optional<Facture> camOptional =  factureRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Facture not found ");
        }

        Facture campanyExisting = camOptional.get();
        factureMapper.partialUpdate(campanyExisting, dto);

        return factureMapper.toDto(save(campanyExisting));

    }

    @Override
    public FactureDto findById(String id) throws NotFoundException {
        Optional<Facture> campOptional = factureRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Facture not found ");
        }
        return factureMapper.toDto(campOptional.get());
    }

    @Override
    public List<FactureDto> findAll() {
        return factureRepository.findAll().stream()
                .map(factureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!factureRepository.existsById(id)) {
            throw new NotFoundException("Facture not found ");
        }

        factureRepository.deleteById(id);


    }


    @Override
    public FactureDto findByCode(String code) throws NotFoundException {
        Optional<Facture> campOptional = factureRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return factureMapper.toDto(campOptional.get());
    }


    @Override
    public Page<Facture> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  factureRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<Facture> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  factureRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<Facture> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  factureRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<Facture> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  factureRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<FactureDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<FactureDto>  result = factureRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(factureMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Facture> groOptional =  factureRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Facture groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        factureRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Facture> groOptional =  factureRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Facture groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        factureRepository.save(groExisting);

    }



}
