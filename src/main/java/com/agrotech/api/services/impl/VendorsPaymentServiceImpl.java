package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorsPaymentRepository;
import com.agrotech.api.dto.VendorsPayementDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorsPayementsMapper;
import com.agrotech.api.model.VendorSKU;
import com.agrotech.api.model.Vendors;
import com.agrotech.api.model.VendorsPayments;
import com.agrotech.api.services.VendorsPaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorsPaymentServiceImpl implements VendorsPaymentsService {

    @Autowired
    private VendorsPaymentRepository vendorsPaymentRepository;
    @Autowired
    private VendorsPayementsMapper vendorsPayementsMapper;




    public VendorsPayments save(VendorsPayments entity) {

        return vendorsPaymentRepository.save(entity);
    }


    @Override
    public VendorsPayementDto create(VendorsPayementDto dto) {

        return vendorsPayementsMapper.toDto(save(vendorsPayementsMapper.toEntity(dto)));

    }

    @Override
    public VendorsPayementDto update(String id, VendorsPayementDto dto) throws NotFoundException {
        Optional<VendorsPayments> camOptional =  vendorsPaymentRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VEndorsPayment not found ");
        }

        VendorsPayments campanyExisting = camOptional.get();
        vendorsPayementsMapper.partialUpdate(campanyExisting, dto);

        return vendorsPayementsMapper.toDto(save(campanyExisting));
    }

    @Override
    public VendorsPayementDto findById(String id) throws NotFoundException {
        Optional<VendorsPayments> campOptional = vendorsPaymentRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorsPayment not found ");
        }
        return vendorsPayementsMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorsPayementDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorsPayementDto> result =  vendorsPaymentRepository.findByIsDeletedAndIdContainingIgnoreCase(false,filter, pageable)
                .stream()
                .map(vendorsPayementsMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<VendorsPayments> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorsPayments> result =  vendorsPaymentRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorsPayments> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorsPayments> result =  vendorsPaymentRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorsPayments> groOptional =  vendorsPaymentRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VendorsPayments groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorsPaymentRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorsPayments> groOptional =  vendorsPaymentRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Vendors not found ");
        }
        VendorsPayments groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorsPaymentRepository.save(groExisting);
    }

    @Override
    public Page<VendorsPayementDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorsPayementDto>  result = vendorsPaymentRepository.findByIsDeletedAndIdContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsPayementsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);



    }

    @Override
    public Page<VendorsPayementDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorsPayementDto>  result = vendorsPaymentRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsPayementsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public List<VendorsPayementDto> findAll() {

        return vendorsPaymentRepository.findAll()
                .stream()
                .map(vendorsPayementsMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public Page<VendorsPayementDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorsPayementDto>  result = vendorsPaymentRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsPayementsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);

    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorsPaymentRepository.existsById(id)) {
            throw new NotFoundException("Campany not found ");
        }

        vendorsPaymentRepository.deleteById(id);


    }
}
