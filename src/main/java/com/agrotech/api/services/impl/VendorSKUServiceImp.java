package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorSKURepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorSKUDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorSKUMapper;
import com.agrotech.api.model.VendorSKU;
import com.agrotech.api.services.VendorSKUService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorSKUServiceImp implements VendorSKUService {

    @Autowired
    private final VendorSKURepository vendorSKURepository ;
    @Autowired
    private final VendorSKUMapper vendorSKUMapper ;

    public VendorSKU save(VendorSKU entity) {
        return vendorSKURepository.save(entity);
    }

    @Override
    public VendorSKUDto create(VendorSKUDto dto) {
        return vendorSKUMapper.toDto(save(vendorSKUMapper.toEntity(dto)));

    }

    @Override
    public VendorSKUDto update(String id, VendorSKUDto dto) throws NotFoundException {

        Optional<VendorSKU> vendorSKUOptional =  vendorSKURepository.findById(id);
        if(vendorSKUOptional.isEmpty()) {
            throw new NotFoundException("Currency not found ");
        }

        VendorSKU vendorSKUExisting = vendorSKUOptional.get();
        vendorSKUMapper.partialUpdate(vendorSKUExisting, dto);

        return vendorSKUMapper.toDto(save(vendorSKUExisting));
    }

    @Override
    public VendorSKUDto findById(String id) throws NotFoundException {
        Optional<VendorSKU> vendorSKU = vendorSKURepository.findById(id);

        if (vendorSKU.isEmpty()) {
            throw new NotFoundException("vendorSKU not found");
        }
        return vendorSKUMapper.toDto(vendorSKU.get());
    }

    @Override
    public List<VendorSKUDto> findAll() {
        return vendorSKURepository.findAll()
                .stream()
                .map(vendorSKUMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorSKU> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("vendorSKUName").ascending());
        Page<VendorSKU>  result =  vendorSKURepository.findByIsDeletedAndVendorSKUNameContainingIgnoreCase(false,filter, pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<VendorSKU> findArchivedPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("vendorSKUName").ascending());
        Page<VendorSKU>  result =  vendorSKURepository.findByIsDeletedAndVendorSKUNameContainingIgnoreCase(true,filter, pageable);
        return result;
    }

    @Override
    public VendorSKU findByname(String name) throws NotFoundException {
        return vendorSKURepository.findByName(name);
    }

    @Override
    public Page<VendorSKUDto> findPage(int pageSize, int pageNumber, String filter) {

        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return vendorSKURepository.findAll(pageable)
                .map(vendorSKUMapper::toDto);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if (!vendorSKURepository.existsById(id)) {
            throw new NotFoundException("vendorSKU not found");
        }

        vendorSKURepository.deleteById(id);
    }

    @Override
    public VendorSKUDto findByCode(String vendorSKUCode) throws NotFoundException {
        Optional<VendorSKU> campOptional = vendorSKURepository.findByCode(vendorSKUCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return vendorSKUMapper.toDto(campOptional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorSKU> groOptional =  vendorSKURepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("vendorSKU not found ");
        }
        VendorSKU groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorSKURepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorSKU> groOptional =  vendorSKURepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("vendorSKU not found ");
        }
        VendorSKU groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorSKURepository.save(groExisting);

    }

    @Override
    public Page<VendorSKUDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorSKUDto>  result = vendorSKURepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorSKUMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

}
