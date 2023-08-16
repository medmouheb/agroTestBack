package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypePurchaserRepository;
import com.agrotech.api.dto.VendorTypePurchaserDto;
import com.agrotech.api.dto.VendorTypesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypePurchaserMapper;
import com.agrotech.api.model.VendorTypePurchaser;
import com.agrotech.api.services.VendorTypePurchaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VendorTypePurchaserServiceImpl implements VendorTypePurchaserService {

    @Autowired
    private VendorTypePurchaserRepository vendorTypePurchaserRepository;

    @Autowired
    private VendorTypePurchaserMapper vendorTypePurchaserMapper;


    public VendorTypePurchaser save(VendorTypePurchaser entity){

        return vendorTypePurchaserRepository.save(entity);
    }

    @Override
    public VendorTypePurchaserDto create(VendorTypePurchaserDto dto) {
        return vendorTypePurchaserMapper.toDto(save(vendorTypePurchaserMapper.toEntity(dto)));
    }

    @Override
    public VendorTypePurchaserDto update(String id, VendorTypePurchaserDto dto) throws NotFoundException {
        Optional<VendorTypePurchaser> camOptional =  vendorTypePurchaserRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }

        VendorTypePurchaser campanyExisting = camOptional.get();
        vendorTypePurchaserMapper.partialUpdate(campanyExisting, dto);

        return vendorTypePurchaserMapper.toDto(save(campanyExisting));

    }

    @Override
    public List<VendorTypePurchaserDto> findAll() {
        return vendorTypePurchaserRepository.findAll().stream()
                .map(vendorTypePurchaserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypePurchaserDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypePurchaserRepository.existsById(id)) {
            throw new NotFoundException("VendorTypePurchase not found ");
        }

        vendorTypePurchaserRepository.deleteById(id);
    }

    @Override
    public VendorTypePurchaserDto findById(String id) throws NotFoundException {
        Optional<VendorTypePurchaser> campOptional = vendorTypePurchaserRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePurchase not found ");
        }
        return vendorTypePurchaserMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorTypePurchaserDto> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypePurchaserDto> result =  vendorTypePurchaserRepository.findByIsDeletedAndIdContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypePurchaserMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypePurchaser> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypePurchaser> result =  vendorTypePurchaserRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypePurchaser> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypePurchaser> result =  vendorTypePurchaserRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypePurchaser> groOptional =  vendorTypePurchaserRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePurchase not found ");
        }
        VendorTypePurchaser groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypePurchaserRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypePurchaser> groOptional =  vendorTypePurchaserRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePurchase not found ");
        }
        VendorTypePurchaser groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypePurchaserRepository.save(groExisting);
    }

    @Override
    public Page<VendorTypePurchaserDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypePurchaserDto>  result = vendorTypePurchaserRepository.findByIsDeletedAndIdContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypePurchaserMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypePurchaserDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypePurchaserDto>  result = vendorTypePurchaserRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypePurchaserMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
