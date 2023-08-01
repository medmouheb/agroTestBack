package com.agrotech.api.services.impl;

import com.agrotech.api.model.SalesSKU;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotech.api.Repository.WarehouseRepository;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.dto.WarehouseDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.WarehouseMapper;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.Warehouse;
import com.agrotech.api.services.WarehouseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
	
	@Autowired
    private final WarehouseRepository warehouseRepository;
	@Autowired
    private final WarehouseMapper warehouseMapper;

    private Warehouse save(Warehouse entity){
        return warehouseRepository.save(entity);
    }
    
    
    @Override
    public WarehouseDto create(WarehouseDto warehouseDto) {
        return warehouseMapper.toDto(
                save(
                        warehouseMapper.toEntity(warehouseDto)
                )
        );
    }

    @Override
    public WarehouseDto update(String id, WarehouseDto warehouseDto) throws NotFoundException {
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Warehouse not found");
        }

        Warehouse existing = optional.get();
        warehouseMapper.partialUpdate(existing, warehouseDto);
        return warehouseMapper.toDto(
                save(existing)
        );
    }

    @Override
    public WarehouseDto findById(String id) throws NotFoundException {
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Warehouse not found");
        }
        return warehouseMapper.toDto(optional.get());
    }

    @Override
    public List<WarehouseDto> findAll() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Warehouse> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Warehouse>  result =  warehouseRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<Warehouse> findArchivedPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Warehouse>  result =  warehouseRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
        return result;
    }

    @Override
    public Warehouse findByname(String name) throws NotFoundException {
        return warehouseRepository.findByName(name);
    }

    @Override
    public Page<WarehouseDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<WarehouseDto>  result = warehouseRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!warehouseRepository.existsById(id)){
            throw new NotFoundException("Warehouse not found");
        }
        warehouseRepository.deleteById(id);
    }

    @Override
    public WarehouseDto findByCode(String code) throws NotFoundException {
        Optional<Warehouse> optional = warehouseRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        return warehouseMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Warehouse> groOptional =  warehouseRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Warehouse not found ");
        }
        Warehouse groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        warehouseRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Warehouse> groOptional =  warehouseRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Warehouse not found ");
        }
        Warehouse groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        warehouseRepository.save(groExisting);

    }

    @Override
    public Page<WarehouseDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<WarehouseDto>  result = warehouseRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(warehouseMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
