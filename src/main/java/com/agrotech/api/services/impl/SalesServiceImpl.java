package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.agrotech.api.dto.SalesSkuDto;
import com.agrotech.api.model.SalesSKU;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.SalesRepository;
import com.agrotech.api.dto.SalesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.ProduitMapper;
import com.agrotech.api.mapper.SalesMapper;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.Sales;
import com.agrotech.api.services.SalesServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesServices {

	@Autowired
    private final SalesRepository salesRepository;
	@Autowired
    private final SalesMapper salesMapper;

    public Sales save(Sales sales) {
        return salesRepository.save(sales);
    }

    @Override
    public SalesDto create(SalesDto dto) {
        return salesMapper.toDto(
                save(
                        salesMapper.toEntity(dto)
                )
        );
    }
    @Override
    public SalesDto update(String id, SalesDto dto) throws NotFoundException {
        Optional<Sales> sales = salesRepository.findById(id);

        if (sales.isEmpty()) {
            throw new NotFoundException("Sales not found");
        }

        Sales salesExisting = sales.get();
        salesMapper.partialUpdate(salesExisting, dto);

        return salesMapper.toDto(
                save(salesExisting)
        );
    }

    @Override
    public Page<Sales> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Sales>  result =  salesRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<Sales> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Sales>  result =  salesRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
        return result;
    }

    @Override
    public Sales findByname(String name) throws NotFoundException {
        return salesRepository.findByName(name);
    }

    @Override
    public SalesDto findById(String id) throws NotFoundException {
        Optional<Sales> sales = salesRepository.findById(id);

        if (sales.isEmpty()) {
            throw new NotFoundException("Sales not found");
        }
        return salesMapper.toDto(sales.get());
    }

    @Override
    public List<SalesDto> findAll() {
        return salesRepository.findAll()
                .stream()
                .map(salesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SalesDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );

        return salesRepository.findAll(pageable)
                .map(salesMapper::toDto);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if (!salesRepository.existsById(id)) {
            throw new NotFoundException("Sales not found");
        }

        salesRepository.deleteById(id);
    }


    @Override
    public Sales saves(Sales sales) {
      return  salesRepository.save(sales);
    }

    @Override
    public SalesDto findByCode(String code) throws NotFoundException {
        Optional<Sales> optional = salesRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Sales not found");
        }
        return salesMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Sales> groOptional =  salesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Sales groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        salesRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Sales> groOptional =  salesRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Sales groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        salesRepository.save(groExisting);

    }

    @Override
    public Page<SalesDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<SalesDto>  result = salesRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(salesMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

}
