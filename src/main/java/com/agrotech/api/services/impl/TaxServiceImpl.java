package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.Repository.TaxRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.TaxDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CampanyMapper;
import com.agrotech.api.mapper.TaxMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Tax;
import com.agrotech.api.services.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository taxRepository ;
    @Autowired
    private TaxMapper taxMapper ;



    public Tax save(Tax dto) {

        return taxRepository.save(dto);

    }


    @Override
    public TaxDto create(TaxDto dto) {
        return taxMapper.toDto(save(taxMapper.toEntity(dto)));

    }



    @Override
    public TaxDto update(String id, TaxDto dto) throws NotFoundException {

        Optional<Tax> camOptional =  taxRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Tax not found ");
        }

        Tax campanyExisting = camOptional.get();
        taxMapper.partialUpdate(campanyExisting, dto);

        return taxMapper.toDto(save(campanyExisting));

    }

    @Override
    public TaxDto findById(String id) throws NotFoundException {
        Optional<Tax> campOptional = taxRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Tax not found ");
        }
        return taxMapper.toDto(campOptional.get());
    }

    @Override
    public List<TaxDto> findAll() {
        return taxRepository.findAll().stream()
                .map(taxMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!taxRepository.existsById(id)) {
            throw new NotFoundException("Tax not found ");
        }

        taxRepository.deleteById(id);


    }


    @Override
    public TaxDto findByCode(String code) throws NotFoundException {
        Optional<Tax> campOptional = taxRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Tax not found ");
        }
        return taxMapper.toDto(campOptional.get());
    }
    @Override
    public Page<TaxDto> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<TaxDto> result =  taxRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(taxMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Tax> getpages(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());

        return taxRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
    }

    @Override
    public Page<Tax> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());

        return taxRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
    }

    @Override
    public Page<TaxDto> findPage(int pageSize, int pageNumber, String filter) {

        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<TaxDto>  result = taxRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(taxMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Tax> groOptional =  taxRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Tax not found ");
        }
        Tax groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        taxRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Tax> groOptional =  taxRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Tax not found ");
        }
        Tax groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        taxRepository.save(groExisting);

    }

    @Override
    public Tax findByname(String name) throws NotFoundException {
        return taxRepository.findByName(name);
    }

    @Override
    public Page<TaxDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<TaxDto>  result = taxRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(taxMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public List<Tax> findBynamee() throws NotFoundException {
        return taxRepository.findAll();


    }

    @Override
    public Page<TaxDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<TaxDto>  result = taxRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(taxMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
