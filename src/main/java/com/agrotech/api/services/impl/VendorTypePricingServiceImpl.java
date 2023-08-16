package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypePricingRepository;
import com.agrotech.api.dto.VendorTypePricingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypePricingMapper;
import com.agrotech.api.model.VendorTypePricing;
import com.agrotech.api.model.VendorTypeProduct;
import com.agrotech.api.services.VendorTypePricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VendorTypePricingServiceImpl implements VendorTypePricingService {


    @Autowired
    private VendorTypePricingRepository vendorTypePricingRepository;

    @Autowired
    private VendorTypePricingMapper vendorTypePricingMapper;



    private VendorTypePricing save(VendorTypePricing entity){

        return vendorTypePricingRepository.save(entity);
    }

    @Override
    public VendorTypePricingDto create(VendorTypePricingDto dto) {

            return vendorTypePricingMapper.toDto(save(vendorTypePricingMapper.toEntity(dto)));

    }

    @Override
    public VendorTypePricingDto update(String id, VendorTypePricingDto dto) throws NotFoundException {
        Optional<VendorTypePricing> camOptional =  vendorTypePricingRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        VendorTypePricing campanyExisting = camOptional.get();
        vendorTypePricingMapper.partialUpdate(campanyExisting, dto);

        return vendorTypePricingMapper.toDto(save(campanyExisting));
    }

    @Override
    public VendorTypePricingDto findById(String id) throws NotFoundException {
        Optional<VendorTypePricing> campOptional = vendorTypePricingRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("vendorTypePricing not found ");
        }
        return vendorTypePricingMapper.toDto(campOptional.get());
    }

    @Override
    public List<VendorTypePricingDto> findAll() {
        return vendorTypePricingRepository.findAll().stream()
                .map(vendorTypePricingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypePricingDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorTypePricingRepository.existsById(id)) {
            throw new NotFoundException("vendorTypePricing not found ");
        }

        vendorTypePricingRepository.deleteById(id);
    }

    @Override
    public VendorTypePricingDto findByCurrencyCode(String CurrencyCode) throws NotFoundException {

        Optional<VendorTypePricing> campOptional = vendorTypePricingRepository.findByCurrencyCode(CurrencyCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("vendorTypePricing not found ");
        }
        return vendorTypePricingMapper.toDto(campOptional.get());

    }

    @Override
    public Page<VendorTypePricingDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("CurrencyName").ascending());
        List<VendorTypePricingDto> result =  vendorTypePricingRepository.findByIsDeletedAndCurrencyNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypePricingMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypePricing> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorTypePricing> result =  vendorTypePricingRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypePricing> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("CurrencyName").ascending());
        Page<VendorTypePricing> result =  vendorTypePricingRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypePricing> groOptional =  vendorTypePricingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePricing not found ");
        }
        VendorTypePricing groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypePricingRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypePricing> groOptional =  vendorTypePricingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePricing not found ");
        }
        VendorTypePricing groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypePricingRepository.save(groExisting);
    }

    @Override
    public VendorTypePricing findByCurrencyName(String CurrencyName) throws NotFoundException {
        return vendorTypePricingRepository.findByCurrencyName(CurrencyName);    }

    @Override
    public Page<VendorTypePricingDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("CurrencyName").ascending());
        List<VendorTypePricingDto> result = vendorTypePricingRepository.findByIsDeletedAndCurrencyNameContainingIgnoreCase(true, filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypePricingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypePricingDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypePricingDto>  result = vendorTypePricingRepository.findByCurrencyNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypePricingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
