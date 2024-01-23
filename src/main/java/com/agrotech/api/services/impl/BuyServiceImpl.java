package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.BuyRepository;
import com.agrotech.api.dto.BuyDto;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BuyMapper;
import com.agrotech.api.model.Buy;
import com.agrotech.api.model.Campany;
import com.agrotech.api.services.BuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl  implements BuyService {


    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private BuyMapper buyMapper;

    public Buy save(Buy dto) {

        return buyRepository.save(dto);

    }

    @Override
    public BuyDto create(BuyDto dto) {
        return buyMapper.toDto(save(buyMapper.toEntity(dto)));
    }

    @Override
    public BuyDto update(String s, BuyDto dto) throws NotFoundException {
        Optional<Buy> camOptional =  buyRepository.findById(s);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Buy not found ");
        }

        Buy campanyExisting = camOptional.get();
        buyMapper.partialUpdate(campanyExisting, dto);

        return buyMapper.toDto(save(campanyExisting));
    }

    @Override
    public BuyDto findById(String s) throws NotFoundException {
        Optional<Buy> campOptional = buyRepository.findById(s);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Buy not found ");
        }
        return buyMapper.toDto(campOptional.get());
    }

    @Override
    public List<BuyDto> findAll() {
        return buyRepository.findAll().stream()
                .map(buyMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public void delete(String s) throws NotFoundException {
        if(!buyRepository.existsById(s)) {
            throw new NotFoundException("buy not found ");
        }
        buyRepository.deleteById(s);
    }

    @Override
    public BuyDto findByCode(String code) throws NotFoundException {
        Optional<Buy> campOptional = buyRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Buy not found ");
        }
        return buyMapper.toDto(campOptional.get());
    }

    @Override
    public Page<BuyDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BuyDto>  result = buyRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(buyMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }




    @Override
    public Page<BuyDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("code").ascending());
        List<BuyDto> result =  buyRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable)
                .stream()
                .map(buyMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public Page<Buy> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("code").ascending());
        return buyRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);
    }

    @Override
    public Page<Buy> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("code").ascending());
        return buyRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Buy> groOptional =  buyRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Buy not found ");
        }
        Buy groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        buyRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Buy> groOptional =  buyRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Buy not found ");
        }
        Buy groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        buyRepository.save(groExisting);
    }

    @Override
    public Page<BuyDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("code").ascending());
        List<BuyDto>  result = buyRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable)
                .stream()
                .map(buyMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public Page<BuyDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<BuyDto>  result = buyRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(buyMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }


}
