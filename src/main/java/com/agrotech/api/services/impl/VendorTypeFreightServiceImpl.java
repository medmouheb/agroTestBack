package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeFreightRepository;
import com.agrotech.api.dto.VendorTypeFreightDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeFreightMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeFreight;
import com.agrotech.api.services.VendorTypeFreightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypeFreightServiceImpl implements VendorTypeFreightService {


    @Autowired
    private VendorTypeFreightRepository vendorTypeFreightRepository;

    @Autowired
    private VendorTypeFreightMapper vendorTypeFreightMapper ;



    private VendorTypeFreight save(VendorTypeFreight entity){
        return vendorTypeFreightRepository.save(entity);
    }


    @Override
    public VendorTypeFreightDto create(VendorTypeFreightDto dto) {
        return vendorTypeFreightMapper.toDto(save(vendorTypeFreightMapper.toEntity(dto)));

    }

    @Override
    public VendorTypeFreightDto update(String id, VendorTypeFreightDto dto) throws NotFoundException {

        Optional<VendorTypeFreight> camOptional =  vendorTypeFreightRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeFreight not found ");
        }

        VendorTypeFreight campanyExisting = camOptional.get();
        vendorTypeFreightMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeFreightMapper.toDto(save(campanyExisting));

    }

    @Override
    public VendorTypeFreightDto findById(String id) throws NotFoundException {
        Optional<VendorTypeFreight> campOptional = vendorTypeFreightRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return vendorTypeFreightMapper.toDto(campOptional.get());    }

    @Override
    public List<VendorTypeFreightDto> findAll() {
        return vendorTypeFreightRepository.findAll().stream()
                .map(vendorTypeFreightMapper::toDto)
                .collect(Collectors.toList());    }

    @Override
    public Page<VendorTypeFreightDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeFreightDto>  result = vendorTypeFreightRepository.findByFreightTermNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeFreightMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);

    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorTypeFreightRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeFreight not found ");
        }

        vendorTypeFreightRepository.deleteById(id);
    }

    @Override
    public VendorTypeFreightDto findByFreightTermCode(String FreightTermCode) throws NotFoundException {
        Optional<VendorTypeFreight> campOptional = vendorTypeFreightRepository.findByFreightTermCode(FreightTermCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeFreight not found ");
        }
        return vendorTypeFreightMapper.toDto(campOptional.get());    }

    @Override
    public Page<VendorTypeFreightDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("FreightTypeName").ascending());
        List<VendorTypeFreightDto> result =  vendorTypeFreightRepository.findByIsDeletedAndFreightTermNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeFreightMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeFreight> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("FreightTypeName").ascending());
        Page<VendorTypeFreight> result =  vendorTypeFreightRepository.findByIsDeleted(false, pageable);

        return result;    }

    @Override
    public Page<VendorTypeFreight> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("FreightTypeName").ascending());
        Page<VendorTypeFreight> result =  vendorTypeFreightRepository.findByIsDeleted(true, pageable);
        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypeFreight> groOptional =  vendorTypeFreightRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeFreight not found ");
        }
        VendorTypeFreight groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeFreightRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeFreight> groOptional =  vendorTypeFreightRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeFreight not found ");
        }
        VendorTypeFreight groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeFreightRepository.save(groExisting);
    }

    @Override
    public VendorTypeFreight findByFreightTermName(String FreightTermName) throws NotFoundException {
        return vendorTypeFreightRepository.findByFreightTermName(FreightTermName);
    }

    @Override
    public Page<VendorTypeFreightDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("FreightTermName").ascending());
        List<VendorTypeFreightDto>  result = vendorTypeFreightRepository.findByIsDeletedAndFreightTermNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeFreightMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeFreightDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeFreightDto>  result = vendorTypeFreightRepository.findByFreightTermNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeFreightMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
