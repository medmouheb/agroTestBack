package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorsRepository;
import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorsMapper;
import com.agrotech.api.model.Vendors;
import com.agrotech.api.services.VendorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorsServiceImpl implements VendorsService {

    @Autowired
    private VendorsRepository vendorsRepository;

    @Autowired
    private VendorsMapper vendorsMapper;


    public Vendors save(Vendors entity) {
        return vendorsRepository.save(entity);
    }


    @Override
    public VendorsDto create(VendorsDto dto) {

        return vendorsMapper.toDto(save(vendorsMapper.toEntity(dto)));
    }

    @Override
    public VendorsDto update(String id, VendorsDto dto) throws NotFoundException {
        Optional<Vendors> camOptional =  vendorsRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Vendors not found ");
        }

        Vendors campanyExisting = camOptional.get();
        vendorsMapper.partialUpdate(campanyExisting, dto);

        return vendorsMapper.toDto(save(campanyExisting));

    }

    @Override
    public VendorsDto findById(String id) throws NotFoundException {
        Optional<Vendors> campOptional = vendorsRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Vendors not found ");
        }
        return vendorsMapper.toDto(campOptional.get());
    }

    @Override
    public List<VendorsDto> findAll() {
        return vendorsRepository.findAll().stream()
                .map(vendorsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorsDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorsDto>  result = vendorsRepository.findByVendorName01ContainingIgnoreCase(filter, pageable)
                .stream()
                .map(vendorsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorsRepository.existsById(id)) {
            throw new NotFoundException("Vendors not found ");
        }

        vendorsRepository.deleteById(id);
    }

    @Override
    public VendorsDto findByVendorCode(String VendorCode) throws NotFoundException {
        Optional<Vendors> campOptional = vendorsRepository.findByVendorCode(VendorCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Vendors not found ");
        }
        return vendorsMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("AddressLine01").ascending());
        List<VendorsDto> result =  vendorsRepository.findByIsDeletedAndVendorName01ContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Vendors> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("AddressLine01").ascending());
        Page<Vendors> result =  vendorsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<Vendors> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("AddressLine01").ascending());
        Page<Vendors> result =  vendorsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Vendors> groOptional =  vendorsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Vendors not found ");
        }
        Vendors groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Vendors> groOptional =  vendorsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Vendors not found ");
        }
        Vendors groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorsRepository.save(groExisting);
    }

    @Override
    public Vendors findByVendorName01(String VendorName01) throws NotFoundException {

        return vendorsRepository.findByVendorName01(VendorName01);
    }

    @Override
    public Page<VendorsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("AddressLine01").ascending());
        List<VendorsDto>  result = vendorsRepository.findByIsDeletedAndVendorName01ContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorsDto>  result = vendorsRepository.findByVendorName01ContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
