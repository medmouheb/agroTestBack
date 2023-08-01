package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.WillayaRepository;
import com.agrotech.api.dto.WillayaDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.WillayaMapper;
import com.agrotech.api.model.Willaya;
import com.agrotech.api.services.WilayaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WillayaServiceImpl implements WilayaService {

    @Autowired
    private final WillayaRepository willayaRepository ;
    @Autowired
    private final WillayaMapper willayaMappery ;

    public Willaya saveWillaya(Willaya entity) {
        return willayaRepository.save(entity);
    }


    @Override
    public WillayaDto create(WillayaDto dto) {
        return willayaMappery.toDto(saveWillaya(willayaMappery.toEntity(dto))) ;
    }

    @Override
    public WillayaDto update(String id, WillayaDto dto) throws NotFoundException {

        Optional<Willaya> willayaOptional =  willayaRepository.findById(id);
        if(willayaOptional.isEmpty()) {
            throw new NotFoundException("Currency not found ");
        }

        Willaya willayaExisting = willayaOptional.get();
        willayaMappery.partialUpdate(willayaExisting, dto);

        return willayaMappery.toDto(saveWillaya(willayaExisting));
    }

    @Override
    public Page<WillayaDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<WillayaDto>  result = willayaRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(willayaMappery::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public Page<Willaya> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Willaya>  result =  willayaRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<Willaya> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Willaya>  result =  willayaRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
        return result;
    }

    @Override
    public Willaya findByname(String name) throws NotFoundException {
        return willayaRepository.findByName(name);
    }

    @Override
    public WillayaDto findById(String id) throws NotFoundException {
        Optional<Willaya> willayaOptional = willayaRepository.findById(id);
        if(willayaOptional.isEmpty()) {
            throw new NotFoundException("Willaya not found ");
        }
        return willayaMappery.toDto(willayaOptional.get());
    }

    @Override
    public WillayaDto findByCode(String code) throws NotFoundException {
        Optional<Willaya> willayaOptional = willayaRepository.findByCode(code);
        if(willayaOptional.isEmpty()) {
            throw new NotFoundException("Willaya not found ");
        }
        return willayaMappery.toDto(willayaOptional.get());
    }

    @Override
    public List<WillayaDto> findAll() {
        return willayaRepository.findAll().stream()
                .map(willayaMappery::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!willayaRepository.existsById(id)) {
            throw new NotFoundException("Willaya not found ");
        }

        willayaRepository.deleteById(id);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Willaya> groOptional =  willayaRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Willaya not found ");
        }
        Willaya groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        willayaRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Willaya> groOptional =  willayaRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Willaya not found ");
        }
        Willaya groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        willayaRepository.save(groExisting);

    }

    @Override
    public Page<WillayaDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<WillayaDto>  result = willayaRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(willayaMappery::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }



}
