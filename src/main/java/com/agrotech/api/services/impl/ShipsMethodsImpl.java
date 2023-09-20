package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.ShipMethodsRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.ShipMethodsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.ShipMethodsMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.ShipMethods;
import com.agrotech.api.services.ShipMethodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipsMethodsImpl implements ShipMethodsService {

    @Autowired
    private ShipMethodsMapper shipMethodsMapper;
    @Autowired
    private ShipMethodsRepository shipMethodsRepository;



    public ShipMethods save(ShipMethods dto) {

        return shipMethodsRepository.save(dto);

    }

    @Override
    public ShipMethodsDto create(ShipMethodsDto dto) {
        return shipMethodsMapper.toDto(save(shipMethodsMapper.toEntity(dto)));
    }

    @Override
    public ShipMethodsDto update(String id, ShipMethodsDto dto) throws NotFoundException {
        Optional<ShipMethods> camOptional =  shipMethodsRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("ShipMethods not found ");
        }

        ShipMethods ShipMethodsExisting = camOptional.get();
        shipMethodsMapper.partialUpdate(ShipMethodsExisting, dto);

        return shipMethodsMapper.toDto(save(ShipMethodsExisting));

    }

    @Override
    public ShipMethodsDto findById(String id) throws NotFoundException {
        Optional<ShipMethods> campOptional = shipMethodsRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("ShipMethods not found ");
        }
        return shipMethodsMapper.toDto(campOptional.get());
    }

    @Override
    public List<ShipMethodsDto> findAll() {
        return shipMethodsRepository.findAll().stream()
                .map(shipMethodsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ShipMethodsDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!shipMethodsRepository.existsById(id)) {
            throw new NotFoundException("ShipMethod not found ");
        }

        shipMethodsRepository.deleteById(id);
    }

    @Override
    public ShipMethodsDto findByCode(String code) throws NotFoundException {
        Optional<ShipMethods> campOptional = shipMethodsRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("ShipMethods not found ");
        }
        return shipMethodsMapper.toDto(campOptional.get());
    }

    @Override
    public Page<ShipMethodsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<ShipMethodsDto> result =  shipMethodsRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(shipMethodsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<ShipMethods> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<ShipMethods> result =  shipMethodsRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);

        return result;
    }

    @Override
    public Page<ShipMethods> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<ShipMethods> result =  shipMethodsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<ShipMethods> groOptional =  shipMethodsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("ShipMethods not found ");
        }
        ShipMethods groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        shipMethodsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<ShipMethods> groOptional =  shipMethodsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("ShipMethods not found ");
        }
        ShipMethods groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        shipMethodsRepository.save(groExisting);
    }

    @Override
    public ShipMethods findByname(String name) throws NotFoundException {
        return shipMethodsRepository.findByName(name);
    }

    @Override
    public Page<ShipMethodsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<ShipMethodsDto>  result = shipMethodsRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(shipMethodsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<ShipMethodsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<ShipMethodsDto>  result = shipMethodsRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(shipMethodsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
