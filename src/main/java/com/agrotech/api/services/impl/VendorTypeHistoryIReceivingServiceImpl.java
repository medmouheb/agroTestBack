package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorTypeHistoryIReceivingRepository;
import com.agrotech.api.dto.VendorTypeHistoryIReceivingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorTypeHistoryIReceivingMapper;
import com.agrotech.api.model.VendorTypeHistoryIReceiving;
import com.agrotech.api.services.VendorTypeHistoryIReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorTypeHistoryIReceivingServiceImpl implements VendorTypeHistoryIReceivingService {


    @Autowired
    private VendorTypeHistoryIReceivingRepository vendorTypeHistoryIReceivingRepository;

    @Autowired
    private VendorTypeHistoryIReceivingMapper vendorTypeHistoryIReceivingMapper ;



    private VendorTypeHistoryIReceiving save(VendorTypeHistoryIReceiving entity){
        return vendorTypeHistoryIReceivingRepository.save(entity);
    }

    @Override
    public VendorTypeHistoryIReceivingDto create(VendorTypeHistoryIReceivingDto dto) {
        return vendorTypeHistoryIReceivingMapper.toDto(save(vendorTypeHistoryIReceivingMapper.toEntity(dto)));
    }

    @Override
    public VendorTypeHistoryIReceivingDto update(String id, VendorTypeHistoryIReceivingDto dto) throws NotFoundException {
        Optional<VendorTypeHistoryIReceiving> camOptional =  vendorTypeHistoryIReceivingRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryReceiving not found ");
        }

        VendorTypeHistoryIReceiving campanyExisting = camOptional.get();
        vendorTypeHistoryIReceivingMapper.partialUpdate(campanyExisting, dto);

        return vendorTypeHistoryIReceivingMapper.toDto(save(campanyExisting));
    }

    @Override
    public List<VendorTypeHistoryIReceivingDto> findAll() {
        return vendorTypeHistoryIReceivingRepository.findAll().stream()
                .map(vendorTypeHistoryIReceivingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorTypeHistoryIReceivingDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorTypeHistoryIReceivingDto>  result = vendorTypeHistoryIReceivingRepository.findByApprovalUserIDContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeHistoryIReceivingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!vendorTypeHistoryIReceivingRepository.existsById(id)) {
            throw new NotFoundException("VendorTypeHistoryReceiving not found ");
        }

        vendorTypeHistoryIReceivingRepository.deleteById(id);
    }

    @Override
    public VendorTypeHistoryIReceivingDto findById(String id) throws NotFoundException {
        Optional<VendorTypeHistoryIReceiving> campOptional = vendorTypeHistoryIReceivingRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryReceiving not found ");
        }
        return vendorTypeHistoryIReceivingMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorTypeHistoryIReceivingDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ApprovalUserID").ascending());
        List<VendorTypeHistoryIReceivingDto> result =  vendorTypeHistoryIReceivingRepository.findByIsDeletedAndApprovalUserIDContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorTypeHistoryIReceivingMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeHistoryIReceiving> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ApprovalUserID").ascending());
        Page<VendorTypeHistoryIReceiving> result =  vendorTypeHistoryIReceivingRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorTypeHistoryIReceiving> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ApprovalUserID").ascending());
        Page<VendorTypeHistoryIReceiving> result =  vendorTypeHistoryIReceivingRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorTypeHistoryIReceiving> groOptional =  vendorTypeHistoryIReceivingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorTypeHistoryReceiving not found ");
        }
        VendorTypeHistoryIReceiving groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorTypeHistoryIReceivingRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorTypeHistoryIReceiving> groOptional =  vendorTypeHistoryIReceivingRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VendorTypeHistoryIReceiving groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorTypeHistoryIReceivingRepository.save(groExisting);
    }

    @Override
    public VendorTypeHistoryIReceiving findByApprovalUserID(String ApprovalUserID) throws NotFoundException {
        return vendorTypeHistoryIReceivingRepository.findByApprovalUserID(ApprovalUserID);    }

    @Override
    public Page<VendorTypeHistoryIReceivingDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorTypeHistoryIReceivingDto>  result = vendorTypeHistoryIReceivingRepository.findByIsDeletedAndApprovalUserIDContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeHistoryIReceivingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorTypeHistoryIReceivingDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorTypeHistoryIReceivingDto>  result = vendorTypeHistoryIReceivingRepository.findByApprovalUserIDContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorTypeHistoryIReceivingMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
