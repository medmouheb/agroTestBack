package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeHistoryIInvoicesRepository;
import com.agrotech.api.dto.VendorTypeHistoryIInvoicesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeHistoryIInvoicesMapper;
import com.agrotech.api.model.VendorTypeHistoryIInvoices;
import com.agrotech.api.services.VendorTypeHistoryIInvoicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypeHistoryIInvoicesServiceImpl implements VendorTypeHistoryIInvoicesService{

    @Autowired
    private VendorTypeHistoryIInvoicesRepository vendorTypeHistoryIInvoicesRepository;

    @Autowired
    private VendorTypeHistoryIInvoicesMapper vendorTypeHistoryIInvoicesMapper;



    private VendorTypeHistoryIInvoices save(VendorTypeHistoryIInvoices entity){
        return vendorTypeHistoryIInvoicesRepository.save(entity);
    }


    @Override
    public VendorTypeHistoryIInvoicesDto create(VendorTypeHistoryIInvoicesDto dto) {
        return vendorTypeHistoryIInvoicesMapper.toDto(save(vendorTypeHistoryIInvoicesMapper.toEntity(dto)));
    }

    @Override
    public VendorTypeHistoryIInvoicesDto update(String id, VendorTypeHistoryIInvoicesDto dto) throws NotFoundException {
        Optional<VendorTypeHistoryIInvoices> camOptional =  vendorTypeHistoryIInvoicesRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryIInvoices not found ");
        }

        VendorTypeHistoryIInvoices campanyExisting = camOptional.get();
        vendorTypeHistoryIInvoicesMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeHistoryIInvoicesMapper.toDto(save(campanyExisting));
    }

    @Override
    public VendorTypeHistoryIInvoicesDto findById(String id) throws NotFoundException {
        Optional<VendorTypeHistoryIInvoices> campOptional = vendorTypeHistoryIInvoicesRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryIInvoices not found ");
        }
        return vendorTypeHistoryIInvoicesMapper.toDto(campOptional.get());    }

    @Override
    public List<VendorTypeHistoryIInvoicesDto> findAll() {

        return vendorTypeHistoryIInvoicesRepository.findAll().stream()
                .map(vendorTypeHistoryIInvoicesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypeHistoryIInvoicesDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeHistoryIInvoicesDto>  result = vendorTypeHistoryIInvoicesRepository.findByInvoiceTypeContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeHistoryIInvoicesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypeHistoryIInvoicesRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeHistoryIInvoices not found ");
        }

        vendorTypeHistoryIInvoicesRepository.deleteById(id);
    }

    @Override
    public VendorTypeHistoryIInvoicesDto findByInvoiceCode(String InvoiceCode) throws NotFoundException {
        Optional<VendorTypeHistoryIInvoices> campOptional = vendorTypeHistoryIInvoicesRepository.findByInvoiceCode(InvoiceCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryIInvoices not found ");
        }
        return vendorTypeHistoryIInvoicesMapper.toDto(campOptional.get());    }

    @Override
    public Page<VendorTypeHistoryIInvoicesDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("InvoiceCode").ascending());
        List<VendorTypeHistoryIInvoicesDto> result =  vendorTypeHistoryIInvoicesRepository.findByIsDeletedAndInvoiceTypeContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeHistoryIInvoicesMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);

    }

    @Override
    public Page<VendorTypeHistoryIInvoices> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("InvoiceCode").ascending());
        Page<VendorTypeHistoryIInvoices> result =  vendorTypeHistoryIInvoicesRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypeHistoryIInvoices> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("InvoiceCode").ascending());
        Page<VendorTypeHistoryIInvoices> result =  vendorTypeHistoryIInvoicesRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypeHistoryIInvoices> groOptional =  vendorTypeHistoryIInvoicesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryIInvoices not found ");
        }
        VendorTypeHistoryIInvoices groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeHistoryIInvoicesRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeHistoryIInvoices> groOptional =  vendorTypeHistoryIInvoicesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryIInvoices not found ");
        }
        VendorTypeHistoryIInvoices groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeHistoryIInvoicesRepository.save(groExisting);
    }

    @Override
    public VendorTypeHistoryIInvoices findByInvoiceType(String InvoiceType) throws NotFoundException {

        return vendorTypeHistoryIInvoicesRepository.findByInvoiceType(InvoiceType);
    }

    @Override
    public Page<VendorTypeHistoryIInvoicesDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypeHistoryIInvoicesDto>  result = vendorTypeHistoryIInvoicesRepository.findByIsDeletedAndInvoiceTypeContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeHistoryIInvoicesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
    @Override
    public Page<VendorTypeHistoryIInvoicesDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeHistoryIInvoicesDto>  result = vendorTypeHistoryIInvoicesRepository.findByInvoiceTypeContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeHistoryIInvoicesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
