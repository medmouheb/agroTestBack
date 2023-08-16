package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypesRepository;
import com.agrotech.api.dto.VendorTypesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypesMapper;
import com.agrotech.api.model.VendorTypes;
import com.agrotech.api.services.VendorTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypesServiceImpl implements VendorTypesService {


    @Autowired
    private VendorTypesRepository vendorTypesRepository;


    @Autowired
    private VendorTypesMapper vendorTypesMapper ;

    private VendorTypes save(VendorTypes entity){

        return vendorTypesRepository.save(entity);
    }


    @Override
    public VendorTypesDto create(VendorTypesDto dto) {
    return vendorTypesMapper.toDto(save(vendorTypesMapper.toEntity(dto)));
    }

    @Override
    public VendorTypesDto update(String id, VendorTypesDto dto) throws NotFoundException {
        Optional<VendorTypes> camOptional =  vendorTypesRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        VendorTypes campanyExisting = camOptional.get();
        vendorTypesMapper.partialUpdate(campanyExisting, dto);

        return vendorTypesMapper.toDto(save(campanyExisting));
    }

    @Override
    public List<VendorTypesDto> findAll() {
        return vendorTypesRepository.findAll().stream()
                .map(vendorTypesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypesDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypesRepository.existsById(id)) {
            throw new NotFoundException("VendorType not found ");
        }

        vendorTypesRepository.deleteById(id);
    }

    @Override
    public VendorTypesDto findById(String id) throws NotFoundException {
        Optional<VendorTypes> campOptional = vendorTypesRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorType not found ");
        }
        return vendorTypesMapper.toDto(campOptional.get());    }


    @Override
    public Page<VendorTypesDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypesDto> result =  vendorTypesRepository.findByIsDeletedAndIdContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypesMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypes> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypes> result =  vendorTypesRepository.findByIsDeleted(false, pageable);

        return result;
    }


    @Override
    public Page<VendorTypes> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorTypes> result =  vendorTypesRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {

        Optional<VendorTypes> groOptional =  vendorTypesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorType not found ");
        }
        VendorTypes groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypesRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypes> groOptional =  vendorTypesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorType not found ");
        }
        VendorTypes groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypesRepository.save(groExisting);
    }

    @Override
    public Page<VendorTypesDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypesDto>  result = vendorTypesRepository.findByIsDeletedAndIdContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public Page<VendorTypesDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypesDto>  result = vendorTypesRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
