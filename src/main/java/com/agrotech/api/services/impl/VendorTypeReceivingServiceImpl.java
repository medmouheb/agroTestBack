package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeReceivingRepository;
import com.agrotech.api.dto.VendorTypeReceivingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeReceivingMapper;
import com.agrotech.api.model.VendorTypeReceiving;
import com.agrotech.api.services.VendorTypeReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VendorTypeReceivingServiceImpl implements VendorTypeReceivingService {


    @Autowired
    private final VendorTypeReceivingRepository vendorTypeReceivingRepository;

    @Autowired
    private final VendorTypeReceivingMapper vendorTypeReceivingMapper;


    public VendorTypeReceiving save(VendorTypeReceiving entity) {

        return vendorTypeReceivingRepository.save(entity);

    }

    @Override
    public VendorTypeReceivingDto create(VendorTypeReceivingDto dto) {
        return vendorTypeReceivingMapper.toDto(save(vendorTypeReceivingMapper.toEntity(dto)));
    }

    @Override
    public VendorTypeReceivingDto update(String id, VendorTypeReceivingDto dto) throws NotFoundException {
        Optional<VendorTypeReceiving> camOptional =  vendorTypeReceivingRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeReceiving not found ");
        }

        VendorTypeReceiving campanyExisting = camOptional.get();
        vendorTypeReceivingMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeReceivingMapper.toDto(save(campanyExisting));    }

    @Override
    public List<VendorTypeReceivingDto> findAll() {
        return vendorTypeReceivingRepository.findAll().stream()
                .map(vendorTypeReceivingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypeReceivingDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeReceivingDto>  result = vendorTypeReceivingRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeReceivingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypeReceivingRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeReceiving not found ");
        }

        vendorTypeReceivingRepository.deleteById(id);
    }

    @Override
    public VendorTypeReceivingDto findById(String code) throws NotFoundException {
        Optional<VendorTypeReceiving> campOptional = vendorTypeReceivingRepository.findById(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeReceiving not found ");
        }
        return vendorTypeReceivingMapper.toDto(campOptional.get());    }

    @Override
    public Page<VendorTypeReceivingDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypeReceivingDto> result =  vendorTypeReceivingRepository.findByIsDeletedAndIdContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeReceivingMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);    }

    @Override
    public Page<VendorTypeReceiving> getpages(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypeReceiving> result =  vendorTypeReceivingRepository.findByIsDeleted(false, pageable);

        return result;    }

    @Override
    public Page<VendorTypeReceiving> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorTypeReceiving> result =  vendorTypeReceivingRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypeReceiving> groOptional =  vendorTypeReceivingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeReceiving not found ");
        }
        VendorTypeReceiving groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeReceivingRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeReceiving> groOptional =  vendorTypeReceivingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeReceiving not found ");
        }
        VendorTypeReceiving groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeReceivingRepository.save(groExisting);
    }

    @Override
    public Page<VendorTypeReceivingDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorTypeReceivingDto>  result = vendorTypeReceivingRepository.findByIsDeletedAndIdContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeReceivingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeReceivingDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeReceivingDto>  result = vendorTypeReceivingRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeReceivingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
