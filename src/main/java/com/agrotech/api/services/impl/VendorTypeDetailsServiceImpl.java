package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeDetailsRepository;
import com.agrotech.api.dto.VendorTypeDetailsDto;
import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeDetailsMapper;
import com.agrotech.api.model.VendorTypeDetails;
import com.agrotech.api.services.VendorTypeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypeDetailsServiceImpl implements VendorTypeDetailsService {


    @Autowired
    private VendorTypeDetailsRepository vendorTypeDetailsRepository;

    @Autowired
    private VendorTypeDetailsMapper vendorTypeDetailsMapper ;

    private VendorTypeDetails save(VendorTypeDetails entity){
        return vendorTypeDetailsRepository.save(entity);
    }

    @Override
    public VendorTypeDetailsDto create(VendorTypeDetailsDto dto) {
        return vendorTypeDetailsMapper.toDto(save(vendorTypeDetailsMapper.toEntity(dto)));
    }

    @Override
    public VendorTypeDetailsDto update(String id, VendorTypeDetailsDto dto) throws NotFoundException {
        Optional<VendorTypeDetails> camOptional =  vendorTypeDetailsRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetails not found ");
        }

        VendorTypeDetails campanyExisting = camOptional.get();
        vendorTypeDetailsMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeDetailsMapper.toDto(save(campanyExisting));
    }

    @Override
    public List<VendorTypeDetailsDto> findAll() {
        return vendorTypeDetailsRepository.findAll().stream()
                .map(vendorTypeDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypeDetailsDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeDetailsDto>  result = vendorTypeDetailsRepository.findByOrderLineContainingIgnoreCase(filter, pageable)
                .stream()
                .map(vendorTypeDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorTypeDetailsRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }

        vendorTypeDetailsRepository.deleteById(id);
    }

    @Override
    public VendorTypeDetailsDto findById(String code) throws NotFoundException {
        Optional<VendorTypeDetails> campOptional = vendorTypeDetailsRepository.findById(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }
        return vendorTypeDetailsMapper.toDto(campOptional.get());    }

    @Override
    public Page<VendorTypeDetailsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypeDetailsDto> result =  vendorTypeDetailsRepository.findByIsDeletedAndOrderLineContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeDetailsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeDetails> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypeDetails> result =  vendorTypeDetailsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypeDetails> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypeDetails> result =  vendorTypeDetailsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypeDetails> groOptional =  vendorTypeDetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }
        VendorTypeDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeDetailsRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeDetails> groOptional =  vendorTypeDetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }
        VendorTypeDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeDetailsRepository.save(groExisting);
    }

    @Override
    public VendorTypeDetails findByOrderLine(String OrderLine) throws NotFoundException {
        return vendorTypeDetailsRepository.findByOrderLine(OrderLine);    }

    @Override
    public Page<VendorTypeDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypeDetailsDto>  result = vendorTypeDetailsRepository.findByIsDeletedAndOrderLineContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeDetailsDto>  result = vendorTypeDetailsRepository.findByOrderLineContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
