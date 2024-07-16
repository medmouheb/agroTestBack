package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.DeliveryNoteRepository;
import com.agrotech.api.Repository.DeliveryNoteRepository;
import com.agrotech.api.controller.FileController;
import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.DeliveryNoteMapper;
import com.agrotech.api.mapper.DeliveryNoteMapper;
import com.agrotech.api.model.DeliveryNote;
import com.agrotech.api.model.DeliveryNote;
import com.agrotech.api.services.DeliveryNoteService;
import com.agrotech.api.services.DeliveryService;
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
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository ;

    @Autowired
    private DeliveryNoteMapper deliveryNoteMapper ;



    public DeliveryNote save(DeliveryNote dto) {

        return deliveryNoteRepository.save(dto);

    }


    @Override
    public DeliveryNoteDto create(DeliveryNoteDto dto) throws DocumentException, FileNotFoundException {

        return deliveryNoteMapper.toDto(save(deliveryNoteMapper.toEntity(dto)));

    }



    @Override
    public DeliveryNoteDto update(String id, DeliveryNoteDto dto) throws NotFoundException {

        Optional<DeliveryNote> camOptional =  deliveryNoteRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("DeliveryNote not found ");
        }

        DeliveryNote campanyExisting = camOptional.get();
        deliveryNoteMapper.partialUpdate(campanyExisting, dto);

        return deliveryNoteMapper.toDto(save(campanyExisting));

    }

    @Override
    public DeliveryNoteDto findById(String id) throws NotFoundException {
        Optional<DeliveryNote> campOptional = deliveryNoteRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("DeliveryNote not found ");
        }
        return deliveryNoteMapper.toDto(campOptional.get());
    }

    @Override
    public List<DeliveryNoteDto> findAll() {
        return deliveryNoteRepository.findAll().stream()
                .map(deliveryNoteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!deliveryNoteRepository.existsById(id)) {
            throw new NotFoundException("DeliveryNote not found ");
        }

        deliveryNoteRepository.deleteById(id);


    }


    @Override
    public DeliveryNoteDto findByCode(String code, String farmer) throws NotFoundException {
        Optional<DeliveryNote> campOptional = deliveryNoteRepository.findByCodeAndFarmer(code,farmer);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return deliveryNoteMapper.toDto(campOptional.get());
    }


    @Override
    public Page<DeliveryNote> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryNoteRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<DeliveryNote> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryNoteRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<DeliveryNote> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryNoteRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<DeliveryNote> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  deliveryNoteRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<DeliveryNoteDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<DeliveryNoteDto>  result = deliveryNoteRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(deliveryNoteMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<DeliveryNote> groOptional =  deliveryNoteRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        DeliveryNote groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        deliveryNoteRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<DeliveryNote> groOptional =  deliveryNoteRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        DeliveryNote groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        deliveryNoteRepository.save(groExisting);

    }

}
