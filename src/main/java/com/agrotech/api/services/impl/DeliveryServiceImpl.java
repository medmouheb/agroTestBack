package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.CropRepo;
import com.agrotech.api.Repository.DeliveryRepository;
import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.dto.DeliveryDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CropMapper;
import com.agrotech.api.mapper.DeliveryMapper;
import com.agrotech.api.model.Crop;
import com.agrotech.api.model.Delivery;
import com.agrotech.api.services.CropService;
import com.agrotech.api.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository ;

    @Autowired
    private DeliveryMapper deliveryMapper ;

    public Delivery save(Delivery dto) {

        return deliveryRepository.save(dto);

    }


    @Override
    public DeliveryDto create(DeliveryDto dto) {
        return deliveryMapper.toDto(save(deliveryMapper.toEntity(dto)));

    }



    @Override
    public DeliveryDto update(String id, DeliveryDto dto) throws NotFoundException {

        Optional<Delivery> camOptional =  deliveryRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Delivery not found ");
        }

        Delivery campanyExisting = camOptional.get();
        deliveryMapper.partialUpdate(campanyExisting, dto);

        return deliveryMapper.toDto(save(campanyExisting));

    }

    @Override
    public DeliveryDto findById(String id) throws NotFoundException {
        Optional<Delivery> campOptional = deliveryRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("CropDelivery not found ");
        }
        return deliveryMapper.toDto(campOptional.get());
    }

    @Override
    public List<DeliveryDto> findAll() {
        return deliveryRepository.findAll().stream()
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!deliveryRepository.existsById(id)) {
            throw new NotFoundException("Delivery not found ");
        }

        deliveryRepository.deleteById(id);


    }


    @Override
    public DeliveryDto findByCode(String code) throws NotFoundException {
        Optional<Delivery> campOptional = deliveryRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return deliveryMapper.toDto(campOptional.get());
    }


    @Override
    public Page<Delivery> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryRepository.findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<Delivery> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<Delivery> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<Delivery> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryRepository.findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<DeliveryDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<DeliveryDto>  result = deliveryRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Delivery> groOptional =  deliveryRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Delivery groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        deliveryRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Delivery> groOptional =  deliveryRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Delivery groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        deliveryRepository.save(groExisting);

    }

    @Override
    public Delivery findByname(String name) throws NotFoundException {
        return deliveryRepository.findByName(name);
    }

    @Override
    public Page<DeliveryDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<DeliveryDto>  result = deliveryRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g-> false)
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }






}

