package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.Repository.DeliveryInstructionRepository;
import com.agrotech.api.dto.BreedCodeDto;
import com.agrotech.api.dto.DeliveryInstructionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CampanyMapper;
import com.agrotech.api.mapper.DeliveryInstructionMapper;
import com.agrotech.api.model.BreedCode;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.DeliveryInstruction;
import com.agrotech.api.services.DeliveryInstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryInstructionServiceImpl implements DeliveryInstructionService {

    @Autowired
    private DeliveryInstructionRepository deliveryInstructionRepository;
    @Autowired
    private DeliveryInstructionMapper deliveryInstructionMapper;

    public DeliveryInstruction save(DeliveryInstruction dto) {

        return deliveryInstructionRepository.save(dto);

    }
    @Override
    public DeliveryInstructionDto create(DeliveryInstructionDto dto) {
        return deliveryInstructionMapper.toDto(save(deliveryInstructionMapper.toEntity(dto)));
    }

    @Override
    public DeliveryInstructionDto update(String id, DeliveryInstructionDto dto) throws NotFoundException {
        Optional<DeliveryInstruction> camOptional =  deliveryInstructionRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        DeliveryInstruction campanyExisting = camOptional.get();
        deliveryInstructionMapper.partialUpdate(campanyExisting, dto);

        return deliveryInstructionMapper.toDto(save(campanyExisting));
    }

    @Override
    public DeliveryInstructionDto findById(String s) throws NotFoundException {
        Optional<DeliveryInstruction> campOptional = deliveryInstructionRepository.findById(s);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return deliveryInstructionMapper.toDto(campOptional.get());
    }

    @Override
    public List<DeliveryInstructionDto> findAll() {
        return deliveryInstructionRepository.findAll().stream()
                .map(deliveryInstructionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DeliveryInstructionDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String s) throws NotFoundException {
        if(!deliveryInstructionRepository.existsById(s)) {
            throw new NotFoundException("BreedCode not found ");
        }

        deliveryInstructionRepository.deleteById(s);
    }

    @Override
    public DeliveryInstructionDto findBytypeproduct(String producttype) throws NotFoundException {
        return null;
    }

    @Override
    public Page<DeliveryInstructionDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedCodeName").ascending());
        List<DeliveryInstructionDto> result =  deliveryInstructionRepository.findByIsDeletedAndProductTypeContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(deliveryInstructionMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<DeliveryInstruction> getpages(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public Page<DeliveryInstruction> getpagesarchive(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<DeliveryInstruction> groOptional =  deliveryInstructionRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("BreedCode not found ");
        }
        DeliveryInstruction groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        deliveryInstructionRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<DeliveryInstruction> groOptional =  deliveryInstructionRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("BreedCode not found ");
        }
        DeliveryInstruction groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        deliveryInstructionRepository.save(groExisting);
    }

    @Override
    public Page<DeliveryInstructionDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedCodeName").ascending());
        List<DeliveryInstructionDto> result =  deliveryInstructionRepository.findByIsDeletedAndProductTypeContainingIgnoreCase(true,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(deliveryInstructionMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);

    }

    @Override
    public Page<DeliveryInstructionDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        return null;
    }
}
