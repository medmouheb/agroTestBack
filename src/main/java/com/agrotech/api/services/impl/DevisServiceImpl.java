package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.DeliveryRepository;
import com.agrotech.api.Repository.DevisRepository;
import com.agrotech.api.controller.FileController;
import com.agrotech.api.dto.DeliveryDto;
import com.agrotech.api.dto.DevisDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.DeliveryMapper;
import com.agrotech.api.mapper.DevisMapper;
import com.agrotech.api.model.Delivery;
import com.agrotech.api.model.Devis;
import com.agrotech.api.services.DevisService;
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
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository ;

    @Autowired
    private DevisMapper devisMapper ;

    @Autowired
    private FileController fileController;

    public Devis save(Devis dto) {

        return devisRepository.save(dto);

    }


    @Override
    public DevisDto create(DevisDto dto) throws DocumentException, FileNotFoundException {
        dto.setLastUpdate(LocalDateTime.now());
        if(dto.isSendMail()){
            String t=fileController.getatest(dto);
            dto.setFile(t);
        }

        return devisMapper.toDto(save(devisMapper.toEntity(dto)));

    }



    @Override
    public DevisDto update(String id, DevisDto dto) throws NotFoundException {
        dto.setLastUpdate(LocalDateTime.now());

        Optional<Devis> camOptional =  devisRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Devis not found ");
        }

        Devis campanyExisting = camOptional.get();
        devisMapper.partialUpdate(campanyExisting, dto);

        return devisMapper.toDto(save(campanyExisting));

    }

    @Override
    public DevisDto findById(String id) throws NotFoundException {
        Optional<Devis> campOptional = devisRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Devis not found ");
        }
        return devisMapper.toDto(campOptional.get());
    }

    @Override
    public List<DevisDto> findAll() {
        return devisRepository.findAll().stream()
                .map(devisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!devisRepository.existsById(id)) {
            throw new NotFoundException("Devis not found ");
        }

        devisRepository.deleteById(id);


    }


    @Override
    public DevisDto findByCode(String code) throws NotFoundException {
        Optional<Devis> campOptional = devisRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return devisMapper.toDto(campOptional.get());
    }


    @Override
    public Page<Devis> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  devisRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<Devis> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  devisRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<Devis> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  devisRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<Devis> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  devisRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<DevisDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<DevisDto>  result = devisRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(devisMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Devis> groOptional =  devisRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Devis groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        devisRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Devis> groOptional =  devisRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Devis groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        devisRepository.save(groExisting);

    }





}
