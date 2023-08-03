package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.FreighTermsRepository;
import com.agrotech.api.dto.FreightTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FreightTermsMapper;
import com.agrotech.api.model.FreightTerms;
import com.agrotech.api.services.FreightTermsService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class FreightTermsImpl implements FreightTermsService {

    @Autowired
    private FreighTermsRepository freighTermsRepository ;
    @Autowired
    private FreightTermsMapper freightTermsMapper ;

    public FreightTerms save(FreightTerms dto) {

        return freighTermsRepository.save(dto);

    }


    @Override
    public FreightTermsDto create(FreightTermsDto dto) {

            return freightTermsMapper.toDto(save(freightTermsMapper.toEntity(dto)));

    }

    @Override
    public FreightTermsDto update(String s, FreightTermsDto dto) throws NotFoundException {
        Optional<FreightTerms> freightTermsOptional =  freighTermsRepository.findById(s);
        if(freightTermsOptional.isEmpty()) {
            throw new NotFoundException("FreightTerms not found ");
        }

        FreightTerms freightTermsExisting = freightTermsOptional.get();
        freightTermsMapper.partialUpdate(freightTermsExisting, dto);

        return freightTermsMapper.toDto(save(freightTermsExisting));    }

    @Override
    public FreightTermsDto findById(String s) throws NotFoundException {
        Optional<FreightTerms> freightTermsOptional =  freighTermsRepository.findById(s);
        if(freightTermsOptional.isEmpty()) {
            throw new NotFoundException("FreightTerms not found ");
        }
        return freightTermsMapper.toDto(freightTermsOptional.get());
    }

    @Override
    public List<FreightTermsDto> findAll() {
        return freighTermsRepository.findAll().stream().map(freightTermsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<FreightTermsDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("freighttermname").ascending());
        Page<FreightTerms> freightTermsPage = freighTermsRepository.findAll(pageable);
        List<FreightTermsDto> freightTermsDtos = freightTermsPage.getContent().stream().map(freightTermsMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(freightTermsDtos, pageable, freightTermsPage.getTotalElements());

    }

    @Override
    public void delete(String s) throws NotFoundException {
        Optional<FreightTerms> freightTermsOptional =  freighTermsRepository.findById(s);
        if(freightTermsOptional.isEmpty()) {
            throw new NotFoundException("FreightTerms not found ");
        }
        freighTermsRepository.delete(freightTermsOptional.get());


    }

    @Override
    public FreightTermsDto findByfreighttermcode(String freighttermcode) throws NotFoundException {
        Optional<FreightTerms> freightTermsOptional =  freighTermsRepository.findByFreighttermcode(freighttermcode);
        if(freightTermsOptional.isEmpty()) {
            throw new NotFoundException("FreightTerms not found ");
        }
        return freightTermsMapper.toDto(freightTermsOptional.get());
    }

    @Override
    public Page<FreightTermsDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<FreightTerms> freightTermsPage = freighTermsRepository.findAll(pageable);
        List<FreightTermsDto> freightTermsDtos = freightTermsPage.getContent().stream().map(freightTermsMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(freightTermsDtos, pageable, freightTermsPage.getTotalElements());
    }

    @Override
    public Page<FreightTerms> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<FreightTerms> freightTermsPage = freighTermsRepository.findAll(pageable);
        return freightTermsPage;
    }

    @Override
    public Page<FreightTerms> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<FreightTerms> freightTermsPage = freighTermsRepository.findAll(pageable);
        return freightTermsPage;

    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<FreightTerms> freightTermsOptional =  freighTermsRepository.findById(id);
        if(freightTermsOptional.isEmpty()) {
            throw new NotFoundException("FreightTerms not found ");
        }
        FreightTerms freightTerms = freightTermsOptional.get();
        freighTermsRepository.save(freightTerms);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<FreightTerms> freightTermsOptional =  freighTermsRepository.findById(id);
        if(freightTermsOptional.isEmpty()) {
            throw new NotFoundException("FreightTerms not found ");
        }
        FreightTerms freightTerms = freightTermsOptional.get();
        freighTermsRepository.save(freightTerms);


    }

    @Override
    public FreightTerms findByfreighttermname(String freighttermname) throws NotFoundException {
        return freighTermsRepository.findByFreighttermname(freighttermname);

    }
    @Override
    public Page<FreightTermsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<FreightTerms> freightTermsPage = freighTermsRepository.findAll(pageable);
        List<FreightTermsDto> freightTermsDtos = freightTermsPage.getContent().stream().map(freightTermsMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(freightTermsDtos, pageable, freightTermsPage.getTotalElements());

    }

    @Override
    public Page<FreightTermsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<FreightTerms> freightTermsPage = freighTermsRepository.findAll(pageable);
        List<FreightTermsDto> freightTermsDtos = freightTermsPage.getContent().stream().map(freightTermsMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(freightTermsDtos, pageable, freightTermsPage.getTotalElements());

    }
}