package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.FactureRepository;
import com.agrotech.api.Repository.ProductionRepository;
import com.agrotech.api.controller.FileController;
import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.dto.ProductionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FactureMapper;
import com.agrotech.api.mapper.ProductionMapper;
import com.agrotech.api.model.Facture;
import com.agrotech.api.model.Production;
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
    private ProductionRepository productionRepository ;

    @Autowired
    private ProductionMapper productionMapper ;

    @Autowired
    private FileController fileController;

    public Production save(Production dto) {

        return productionRepository.save(dto);

    }


    @Override
    public ProductionDto create(ProductionDto dto) throws DocumentException, FileNotFoundException {


        return productionMapper.toDto(save(productionMapper.toEntity(dto)));

    }



    @Override
    public ProductionDto update(String id, ProductionDto dto) throws NotFoundException {

        Optional<Production> camOptional =  productionRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Production not found ");
        }

        Production campanyExisting = camOptional.get();
        productionMapper.partialUpdate(campanyExisting, dto);

        return productionMapper.toDto(save(campanyExisting));

    }

    @Override
    public ProductionDto findById(String id) throws NotFoundException {
        Optional<Production> campOptional = productionRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Production not found ");
        }
        return productionMapper.toDto(campOptional.get());
    }

    @Override
    public List<ProductionDto> findAll() {
        return productionRepository.findAll().stream()
                .map(productionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!productionRepository.existsById(id)) {
            throw new NotFoundException("Production not found ");
        }

        productionRepository.deleteById(id);


    }


    @Override
    public ProductionDto findByCode(String code) throws NotFoundException {
        Optional<Production> campOptional = productionRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return productionMapper.toDto(campOptional.get());
    }


    @Override
    public Page<Production> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  productionRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<Production> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  productionRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<Production> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  productionRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<Production> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  productionRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<ProductionDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ProductionDto>  result = productionRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(productionMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Production> groOptional =  productionRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Production groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        productionRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Production> groOptional =  productionRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Production groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        productionRepository.save(groExisting);

    }



}
