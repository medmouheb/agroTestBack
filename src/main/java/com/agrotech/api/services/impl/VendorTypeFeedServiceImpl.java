package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeFeedRepository;
import com.agrotech.api.dto.VendorTypeFeedDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeFeedMapper;
import com.agrotech.api.model.VendorTypeDetails;
import com.agrotech.api.model.VendorTypeFeed;
import com.agrotech.api.services.VendorTypeFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypeFeedServiceImpl implements VendorTypeFeedService {


    @Autowired
    private VendorTypeFeedRepository vendorTypeFeedRepository;

    @Autowired
    private VendorTypeFeedMapper vendorTypeFeedMapper ;



    private VendorTypeFeed save(VendorTypeFeed entity){
        return vendorTypeFeedRepository.save(entity);
    }



    @Override
    public VendorTypeFeedDto create(VendorTypeFeedDto dto) {

        return vendorTypeFeedMapper.toDto(save(vendorTypeFeedMapper.toEntity(dto)));
    }

    @Override
    public VendorTypeFeedDto update(String id, VendorTypeFeedDto dto) throws NotFoundException {
        Optional<VendorTypeFeed> camOptional =  vendorTypeFeedRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetails not found ");
        }

        VendorTypeFeed campanyExisting = camOptional.get();
        vendorTypeFeedMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeFeedMapper.toDto(save(campanyExisting));
    }

    @Override
    public List<VendorTypeFeedDto> findAll() {
        return vendorTypeFeedRepository.findAll().stream()
                .map(vendorTypeFeedMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypeFeedDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeFeedDto>  result = vendorTypeFeedRepository.findByPOStateContainingIgnoreCase(filter, pageable)
                .stream()
                .map(vendorTypeFeedMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypeFeedRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }

        vendorTypeFeedRepository.deleteById(id);
    }

    @Override
    public VendorTypeFeedDto findById(String code) throws NotFoundException {
        Optional<VendorTypeFeed> campOptional = vendorTypeFeedRepository.findById(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }
        return vendorTypeFeedMapper.toDto(campOptional.get());
    }


    @Override
    public Page<VendorTypeFeedDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypeFeedDto> result =  vendorTypeFeedRepository.findByIsDeletedAndPOStateContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeFeedMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeFeed> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypeFeed> result =  vendorTypeFeedRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypeFeed> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypeFeed> result =  vendorTypeFeedRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {

        Optional<VendorTypeFeed> groOptional =  vendorTypeFeedRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }
        VendorTypeFeed groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeFeedRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeFeed> groOptional = vendorTypeFeedRepository.findById(id);
        if (groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeDetail not found ");
        }
        VendorTypeFeed groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeFeedRepository.save(groExisting);
    }

    @Override
    public VendorTypeFeed findByPOState(String POState) throws NotFoundException {
        return vendorTypeFeedRepository.findByPOState(POState);
    }

    @Override
    public Page<VendorTypeFeedDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypeFeedDto>  result = vendorTypeFeedRepository.findByIsDeletedAndPOStateContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeFeedMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeFeedDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeFeedDto>  result = vendorTypeFeedRepository.findByPOStateContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeFeedMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
