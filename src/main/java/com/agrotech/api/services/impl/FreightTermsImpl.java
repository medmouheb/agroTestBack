package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.FreighTermsRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.FreightTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FreightTermsMapper;
import com.agrotech.api.model.Campany;
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
    public FreightTermsDto update(String id, FreightTermsDto dto) throws NotFoundException {
        Optional<FreightTerms> camOptional =  freighTermsRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("freight Term not found ");
        }

        FreightTerms campanyExisting = camOptional.get();
        freightTermsMapper.partialUpdate(campanyExisting, dto);

        return freightTermsMapper.toDto(save(campanyExisting));
    }

    @Override
    public FreightTermsDto findById(String id) throws NotFoundException {

            Optional<FreightTerms> campOptional = freighTermsRepository.findById(id);
            if(campOptional.isEmpty()) {
                throw new NotFoundException("Freight Terms not found ");
            }
            return freightTermsMapper.toDto(campOptional.get());

    }

    @Override
    public List<FreightTermsDto> findAll() {
        return freighTermsRepository.findAll().stream()
                .map(freightTermsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FreightTermsDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!freighTermsRepository.existsById(id)) {
            throw new NotFoundException("Campany not found ");
        }

        freighTermsRepository.deleteById(id);

    }

    @Override
    public FreightTermsDto findByfreighttermcode(String freighttermcode) throws NotFoundException {
        Optional<FreightTerms> campOptional = freighTermsRepository.findByFreighttermcode(freighttermcode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return freightTermsMapper.toDto(campOptional.get());
    }

    @Override
    public Page<FreightTermsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<FreightTermsDto> result =  freighTermsRepository.findByIsDeletedAndFreighttermnameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(freightTermsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<FreightTerms> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<FreightTerms> result =  freighTermsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<FreightTerms> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<FreightTerms> result =  freighTermsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<FreightTerms> groOptional =  freighTermsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        FreightTerms groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        freighTermsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<FreightTerms> groOptional =  freighTermsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        FreightTerms groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        freighTermsRepository.save(groExisting);
    }

    @Override
    public FreightTerms findByfreighttermname(String freighttermname) throws NotFoundException {
        return freighTermsRepository.findByFreighttermname(freighttermname);
    }

    @Override
    public Page<FreightTermsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<FreightTermsDto>  result = freighTermsRepository.findByIsDeletedAndFreighttermnameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(freightTermsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<FreightTermsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        return null;
    }
}
