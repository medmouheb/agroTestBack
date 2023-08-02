package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.Repository.DeliveryInstructionRepository;
import com.agrotech.api.dto.DeliveryInstructionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CampanyMapper;
import com.agrotech.api.mapper.DeliveryInstructionMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.DeliveryInstruction;
import com.agrotech.api.services.DeliveryInstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Optional<DeliveryInstruction>delvoptional=deliveryInstructionRepository.findById(id);
        if (delvoptional.isEmpty()){
            throw new NotFoundException("Delivery Instruction not found ");
        }


        return null;
    }

    @Override
    public DeliveryInstructionDto findById(String s) throws NotFoundException {
        return null;
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

    }

    @Override
    public DeliveryInstructionDto findBytypeproduct(String producttype) throws NotFoundException {
        return null;
    }

    @Override
    public Page<DeliveryInstructionDto> findPage1(int pageSize, int pageNumber, String filter) {
        return null;
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

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {

    }

    @Override
    public Page<DeliveryInstructionDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public Page<DeliveryInstructionDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        return null;
    }
}
