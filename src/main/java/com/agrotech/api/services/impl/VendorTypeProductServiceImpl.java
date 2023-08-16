package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeProductRepository;
import com.agrotech.api.dto.VendorTypeProductDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeProductMapper;
import com.agrotech.api.model.VendorTypeProduct;
import com.agrotech.api.services.VendorTypeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypeProductServiceImpl implements VendorTypeProductService {

    @Autowired
    private VendorTypeProductRepository vendorTypeProductRepository;

    @Autowired
    private VendorTypeProductMapper vendorTypeProductMapper ;



    private VendorTypeProduct save(VendorTypeProduct entity){

        return vendorTypeProductRepository.save(entity);
    }

    @Override
    public VendorTypeProductDto create(VendorTypeProductDto dto) {

        return vendorTypeProductMapper.toDto(save(vendorTypeProductMapper.toEntity(dto)));
    }

    @Override
    public VendorTypeProductDto update(String id, VendorTypeProductDto dto) throws NotFoundException {

        Optional<VendorTypeProduct> camOptional =  vendorTypeProductRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeProduct not found ");
        }

        VendorTypeProduct campanyExisting = camOptional.get();
        vendorTypeProductMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeProductMapper.toDto(save(campanyExisting));

    }

    @Override
    public VendorTypeProductDto findById(String id) throws NotFoundException {
        Optional<VendorTypeProduct> campOptional = vendorTypeProductRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeProduct not found ");
        }
        return vendorTypeProductMapper.toDto(campOptional.get());    }

    @Override
    public List<VendorTypeProductDto> findAll() {
        return vendorTypeProductRepository.findAll().stream()
                .map(vendorTypeProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypeProductDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeProductDto>  result = vendorTypeProductRepository.findByProductNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeProductMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);

    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypeProductRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeProduct not found ");
        }

        vendorTypeProductRepository.deleteById(id);
    }

    @Override
    public VendorTypeProductDto findByProductCode(String ProductCode) throws NotFoundException {

        Optional<VendorTypeProduct> campOptional = vendorTypeProductRepository.findByProductCode(ProductCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeProduct not found ");
        }
        return vendorTypeProductMapper.toDto(campOptional.get());

    }

    @Override
    public Page<VendorTypeProductDto> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ProductName").ascending());
        List<VendorTypeProductDto> result =  vendorTypeProductRepository.findByIsDeletedAndProductNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeProductMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeProduct> getpages(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ProductName").ascending());
        Page<VendorTypeProduct> result =  vendorTypeProductRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypeProduct> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ProductName").ascending());
        Page<VendorTypeProduct> result =  vendorTypeProductRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypeProduct> groOptional =  vendorTypeProductRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeProduct not found ");
        }
        VendorTypeProduct groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeProductRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeProduct> groOptional =  vendorTypeProductRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeProduct not found ");
        }
        VendorTypeProduct groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeProductRepository.save(groExisting);

    }

    @Override
    public VendorTypeProduct findByProductName(String ProductName) throws NotFoundException {
        return vendorTypeProductRepository.findByProductName(ProductName);
    }

    @Override
    public Page<VendorTypeProductDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypeProductDto>  result = vendorTypeProductRepository.findByIsDeletedAndProductNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeProductMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeProductDto> findArchivedPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeProductDto>  result = vendorTypeProductRepository.findByProductNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeProductMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);

    }
}
