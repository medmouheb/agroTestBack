package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorsShippingRepository;
import com.agrotech.api.dto.VendorsShippingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorsShippingMapper;
import com.agrotech.api.model.Vendors;
import com.agrotech.api.model.VendorsShipping;
import com.agrotech.api.services.VendorsShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorsShippingServiceImpl implements VendorsShippingService {


    @Autowired
    private VendorsShippingRepository vendorsShippingRepository;

    @Autowired
    private VendorsShippingMapper vendorsShippingMapper ;


    private VendorsShipping save(VendorsShipping entity){
        return vendorsShippingRepository.save(entity);
    }


    @Override
    public VendorsShippingDto create(VendorsShippingDto dto) {
        return vendorsShippingMapper.toDto(save(vendorsShippingMapper.toEntity(dto)));

    }

    @Override
    public VendorsShippingDto update(String id, VendorsShippingDto dto) throws NotFoundException {
        Optional<VendorsShipping> camOptional =  vendorsShippingRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        VendorsShipping campanyExisting = camOptional.get();
        vendorsShippingMapper.partialUpdate(campanyExisting, dto);

        return vendorsShippingMapper.toDto(save(campanyExisting));
    }

    @Override
    public VendorsShippingDto findById(String id) throws NotFoundException {
        Optional<VendorsShipping> campOptional = vendorsShippingRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return vendorsShippingMapper.toDto(campOptional.get());
    }

    @Override
    public List<VendorsShippingDto> findAll() {
        return vendorsShippingRepository.findAll()
                .stream()
                .map(vendorsShippingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<VendorsShippingDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorsShippingRepository.existsById(id)) {
            throw new NotFoundException("VendorsShipping not found ");
        }

        vendorsShippingRepository.deleteById(id);
    }

    @Override
    public VendorsShippingDto findByShippingLocationCode(String ShippingLocationCode) throws NotFoundException {
        Optional<VendorsShipping> campOptional = vendorsShippingRepository.findByShippingLocationCode(ShippingLocationCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorsShipping not found ");
        }
        return vendorsShippingMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorsShippingDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("VendorsShippingName").ascending());
        List<VendorsShippingDto> result =  vendorsShippingRepository.findByIsDeletedAndShippingLocationNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsShippingMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }


    @Override
    public Page<VendorsShipping> getpages(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("VendorsShippingName").ascending());
        Page<VendorsShipping> result =  vendorsShippingRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorsShipping> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorsShipping> result =  vendorsShippingRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorsShipping> groOptional =  vendorsShippingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VendorsShipping groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorsShippingRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorsShipping> groOptional =  vendorsShippingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VendorsShipping groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorsShippingRepository.save(groExisting);

    }

    @Override
    public VendorsShipping findByShippingLocationName(String ShippingLocationName) throws NotFoundException {
        return vendorsShippingRepository.findByShippingLocationName(ShippingLocationName);


    }

    @Override
    public Page<VendorsShippingDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorsShippingDto>  result = vendorsShippingRepository.findByIsDeletedAndShippingLocationNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsShippingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsShippingDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorsShippingDto>  result = vendorsShippingRepository.findByShippingLocationNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsShippingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
