package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypePODetailsRepository;
import com.agrotech.api.dto.VendorTypePODetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypePODetailsMapper;
import com.agrotech.api.model.VendorTypePODetails;
import com.agrotech.api.services.VendorTypePODetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypePODetailsServiceImpl implements VendorTypePODetailsService {


    @Autowired
    private VendorTypePODetailsRepository vendorTypePODetailsRepository;

    @Autowired
    private VendorTypePODetailsMapper vendorTypePODetailsMapper ;



    private VendorTypePODetails save(VendorTypePODetails entity){

        return vendorTypePODetailsRepository.save(entity);
    }



    @Override
    public VendorTypePODetailsDto create(VendorTypePODetailsDto dto) {
        return vendorTypePODetailsMapper.toDto(save(vendorTypePODetailsMapper.toEntity(dto)));
    }

    @Override
    public VendorTypePODetailsDto update(String id, VendorTypePODetailsDto dto) throws NotFoundException {
        Optional<VendorTypePODetails> camOptional =  vendorTypePODetailsRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePODetails not found ");
        }

        VendorTypePODetails campanyExisting = camOptional.get();
        vendorTypePODetailsMapper.partialUpdate(campanyExisting, dto);

        return vendorTypePODetailsMapper.toDto(save(campanyExisting));
    }

    @Override
    public List<VendorTypePODetailsDto> findAll() {
        return vendorTypePODetailsRepository.findAll().stream()
                .map(vendorTypePODetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypePODetailsDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorTypePODetailsRepository.existsById(id)) {
            throw new NotFoundException("VendorTypePODetails not found ");
        }

        vendorTypePODetailsRepository.deleteById(id);

    }

    @Override
    public VendorTypePODetailsDto findById(String id) throws NotFoundException {
        Optional<VendorTypePODetails> campOptional = vendorTypePODetailsRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePODetails not found ");
        }
        return vendorTypePODetailsMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorTypePODetailsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypePODetailsDto> result =  vendorTypePODetailsRepository.findByIsDeletedAndBuyerNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypePODetailsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypePODetails> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorTypePODetails> result =  vendorTypePODetailsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypePODetails> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorTypePODetails> result =  vendorTypePODetailsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypePODetails> groOptional =  vendorTypePODetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePODetails not found ");
        }
        VendorTypePODetails groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypePODetailsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypePODetails> groOptional =  vendorTypePODetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypePODetails not found ");
        }
        VendorTypePODetails groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypePODetailsRepository.save(groExisting);
    }

    @Override
    public VendorTypePODetails findByBuyerName(String BuyerName) throws NotFoundException {
        return vendorTypePODetailsRepository.findByBuyerName(BuyerName);

    }

    @Override
    public Page<VendorTypePODetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {		Pageable pageable = PageRequest.of(
            pageNumber,
            pageSize
    );
        List<VendorTypePODetailsDto>  result = vendorTypePODetailsRepository.findByBuyerNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypePODetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypePODetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypePODetailsDto>  result = vendorTypePODetailsRepository.findByIsDeletedAndBuyerNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypePODetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
