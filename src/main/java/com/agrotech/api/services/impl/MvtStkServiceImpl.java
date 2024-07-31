package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.MvtStkRepository;
import com.agrotech.api.Repository.MvtStkRepository;
import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.Repository.StockRepository;
import com.agrotech.api.controller.FileController;
import com.agrotech.api.dto.MvtStkDto;
import com.agrotech.api.dto.MvtStkDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.MvtStkMapper;
import com.agrotech.api.mapper.MvtStkMapper;
import com.agrotech.api.model.MvtStk;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.Stock;
import com.agrotech.api.services.MvtStkService;
import com.agrotech.api.services.MvtStkService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MvtStkServiceImpl implements MvtStkService {

    @Autowired
    private MvtStkRepository mvtStkRepository ;
    @Autowired
    private ProduitRepository produitRepository ;
    @Autowired
    private StockRepository StockRepository ;

    @Autowired
    private MvtStkMapper mvtStkMapper ;





    public MvtStk save(MvtStk dto) {

        return mvtStkRepository.save(dto);

    }


    @Override
    public MvtStkDto create(MvtStkDto dto) throws DocumentException, FileNotFoundException {
        Stock s=dto.getStock();

        if(dto.getTypeMvt().equals("enter")){
            s.setQuantity(s.getQuantity() +  dto.getQuantite().floatValue());
        }else{
            s.setQuantity(s.getQuantity() -  dto.getQuantite().floatValue());
        }
        StockRepository.save(s);
        System.out.println("ttt:::");
        System.out.println(dto.getStock().getProduct());

        Produit p =produitRepository.findByName(dto.getStock().getProduct());
        if(s.getQuantity()<p.getStockMinimumAlert().floatValue() ){
            System.out.println("getStockMinimumAlert");
        }else if(s.getQuantity()>p.getMaxdepasse().floatValue()){
            System.out.println("getMaxdepasse");
        }


        return mvtStkMapper.toDto(save(mvtStkMapper.toEntity(dto)));

    }



    @Override
    public MvtStkDto update(String id, MvtStkDto dto) throws NotFoundException {

        Optional<MvtStk> camOptional =  mvtStkRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("MvtStk not found ");
        }

        MvtStk campanyExisting = camOptional.get();
        mvtStkMapper.partialUpdate(campanyExisting, dto);

        return mvtStkMapper.toDto(save(campanyExisting));

    }

    @Override
    public MvtStkDto findById(String id) throws NotFoundException {
        Optional<MvtStk> campOptional = mvtStkRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("MvtStk not found ");
        }
        return mvtStkMapper.toDto(campOptional.get());
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll().stream()
                .map(mvtStkMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!mvtStkRepository.existsById(id)) {
            throw new NotFoundException("MvtStk not found ");
        }

        mvtStkRepository.deleteById(id);


    }


    @Override
    public MvtStkDto findByCode(String code , String farmer) throws NotFoundException {
        Optional<MvtStk> campOptional = mvtStkRepository.findByCodeAndFarmer(code, farmer);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return mvtStkMapper.toDto(campOptional.get());
    }


    @Override
    public Page<MvtStk> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<MvtStk> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<MvtStk> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<MvtStk> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<MvtStkDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<MvtStkDto>  result = mvtStkRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(mvtStkMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<MvtStk> groOptional =  mvtStkRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        MvtStk groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        mvtStkRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<MvtStk> groOptional =  mvtStkRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        MvtStk groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        mvtStkRepository.save(groExisting);

    }





}
