package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.SalesSkuRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.dto.SalesSkuDto;
import com.agrotech.api.dto.VendorSKUDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.SalesSkuMapper;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Sales;
import com.agrotech.api.model.SalesSKU;
import com.agrotech.api.model.VendorSKU;
import com.agrotech.api.services.SalesSkuServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesSkuServiceImpl implements SalesSkuServices {

    @Autowired
    private SalesSkuRepository salesSkuRepository ;
    @Autowired
    private SalesSkuMapper salesSkuMapper ;

    public SalesSKU save(SalesSKU dto) {

        return salesSkuRepository.save(dto);

    }

    @Override
    public SalesSkuDto create(SalesSkuDto dto) {
        return salesSkuMapper.toDto(save(salesSkuMapper.toEntity(dto)));

    }

    @Override
    public SalesSkuDto update(String id, SalesSkuDto dto) throws NotFoundException {

        Optional<SalesSKU> camOptional =  salesSkuRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("SalesSku not found ");
        }

        SalesSKU salesSKUExisting = camOptional.get();
        salesSkuMapper.partialUpdate(salesSKUExisting, dto);

        return salesSkuMapper.toDto(save(salesSKUExisting));

    }

    @Override
    public SalesSkuDto findById(String id) throws NotFoundException {
        Optional<SalesSKU> campOptional = salesSkuRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("salesSku not found ");
        }
        return salesSkuMapper.toDto(campOptional.get());
    }

    @Override
    public List<SalesSkuDto> findAll() {
        return salesSkuRepository.findAll().stream()
                .map(salesSkuMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!salesSkuRepository.existsById(id)) {
            throw new NotFoundException("salesSku not found ");
        }

        salesSkuRepository.deleteById(id);
    }

    @Override
    public SalesSkuDto findByCode(String sailorCode) throws NotFoundException {
        Optional<SalesSKU> optional = salesSkuRepository.findByCode(sailorCode);
        if (optional.isEmpty()) {
            throw new NotFoundException("salesSku not found");
        }
        return salesSkuMapper.toDto(optional.get());
    }



    @Override
    public Page<SalesSkuDto> findPage(int pageSize, int pageNumber, String filter) {

        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize);


        return salesSkuRepository.findAll(pageable)
                .map(salesSkuMapper::toDto);
    }

    @Override
    public Page<SalesSKU> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("sailorNameSku").ascending());
        System.out.println("filter");
        System.out.println(filter);
        Page<SalesSKU>  result =  salesSkuRepository.findByIsDeletedAndSailorNameSkuContainingIgnoreCase(false,filter, pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<SalesSKU> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("sailorNameSku").ascending());
        System.out.println("filter");
        System.out.println(filter);
        Page<SalesSKU>  result =  salesSkuRepository.findByIsDeletedAndSailorNameSkuContainingIgnoreCase(true,filter, pageable);
        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<SalesSKU> groOptional =  salesSkuRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        SalesSKU groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        salesSkuRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<SalesSKU> groOptional =  salesSkuRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        SalesSKU groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        salesSkuRepository.save(groExisting);

    }

    @Override
    public Page<SalesSkuDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<SalesSkuDto>  result = salesSkuRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .map(salesSkuMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }


}
