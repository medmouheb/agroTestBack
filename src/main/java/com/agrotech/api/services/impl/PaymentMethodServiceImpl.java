package com.agrotech.api.services.impl;
import com.agrotech.api.Repository.PaymentMethodRepository;
import com.agrotech.api.dto.PaymentMethodDto;
import com.agrotech.api.model.PaymentMethod;
import org.springframework.data.domain.*;



import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.PaymentMethodMapper;
import com.agrotech.api.services.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;



    public PaymentMethod save(PaymentMethod dto) {

        return paymentMethodRepository.save(dto);

    }

    @Override
    public PaymentMethodDto create(PaymentMethodDto dto) {
        return paymentMethodMapper.toDto(save(paymentMethodMapper.toEntity(dto)));
    }

    @Override
    public PaymentMethodDto update(String id, PaymentMethodDto dto) throws NotFoundException {
        Optional<PaymentMethod> camOptional =  paymentMethodRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("PaymentMethod not found ");
        }

        PaymentMethod PaymentMethodExisting = camOptional.get();
        paymentMethodMapper.partialUpdate(PaymentMethodExisting, dto);

        return paymentMethodMapper.toDto(save(PaymentMethodExisting));

    }

    @Override
    public PaymentMethodDto findById(String id) throws NotFoundException {
        Optional<PaymentMethod> campOptional = paymentMethodRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("PaymentMethod not found ");
        }
        return paymentMethodMapper.toDto(campOptional.get());
    }

    @Override
    public List<PaymentMethodDto> findAll() {
        return paymentMethodRepository.findAll().stream()
                .map(paymentMethodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PaymentMethodDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!paymentMethodRepository.existsById(id)) {
            throw new NotFoundException("ShipMethod not found ");
        }

        paymentMethodRepository.deleteById(id);
    }

    @Override
    public PaymentMethodDto findByCode(String code) throws NotFoundException {
        Optional<PaymentMethod> campOptional = paymentMethodRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("PaymentMethod not found ");
        }
        return paymentMethodMapper.toDto(campOptional.get());
    }

    @Override
    public Page<PaymentMethodDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<PaymentMethodDto> result =  paymentMethodRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
                .stream()
                .map(paymentMethodMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<PaymentMethod> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());

        return paymentMethodRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
    }

    @Override
    public Page<PaymentMethod> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());

        return paymentMethodRepository.findByIsDeleted(true, pageable);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<PaymentMethod> groOptional =  paymentMethodRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("PaymentMethod not found ");
        }
        PaymentMethod groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        paymentMethodRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<PaymentMethod> groOptional =  paymentMethodRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("PaymentMethod not found ");
        }
        PaymentMethod groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        paymentMethodRepository.save(groExisting);
    }

    @Override
    public PaymentMethod findByname(String name) throws NotFoundException {
        return paymentMethodRepository.findByName(name);
    }

    @Override
    public Page<PaymentMethodDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<PaymentMethodDto>  result = paymentMethodRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(paymentMethodMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<PaymentMethodDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<PaymentMethodDto>  result = paymentMethodRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                .map(paymentMethodMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
