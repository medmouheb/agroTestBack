package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BuyersMapper;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.services.BuyersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuyersServiceImpl implements BuyersService {

    @Autowired
    private BuyersRepository buyersRepository ;

    @Autowired
    private BuyersMapper buyersMapper ;


    public Buyers save(Buyers dto) {

        return buyersRepository.save(dto);

    }

    @Override
    public BuyersDto create(BuyersDto dto) {
        return buyersMapper.toDto(save(buyersMapper.toEntity(dto)));
    }

    @Override
    public BuyersDto update(String id, BuyersDto dto) throws NotFoundException {
        Optional<Buyers> camOptional =  buyersRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Buyers not found ");
        }

        Buyers campanyExisting = camOptional.get();
        buyersMapper.partialUpdate(campanyExisting, dto);

        return buyersMapper.toDto(save(campanyExisting));    }

    @Override
    public BuyersDto findById(String id) throws NotFoundException {
        Optional<Buyers> campOptional = buyersRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Buyers not found ");
        }
        return buyersMapper.toDto(campOptional.get());
    }


    @Override
    public List<BuyersDto> findAll() {
        return buyersRepository.findAll().stream()
                .map(buyersMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BuyersDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BuyersDto>  result = buyersRepository.findByBuyerNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(buyersMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!buyersRepository.existsById(id)) {
            throw new NotFoundException("Buyers not found ");
        }

        buyersRepository.deleteById(id);
    }

    @Override
    public BuyersDto findByBuyersCode(String BuyersCode) throws NotFoundException {
        Optional<Buyers> campOptional = buyersRepository.findByBuyerCode(BuyersCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Buyers not found ");
        }
        return buyersMapper.toDto(campOptional.get());    }

    @Override
    public Page<BuyersDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        List<BuyersDto> result =  buyersRepository.findByIsDeletedAndBuyerNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(buyersMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Buyers> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        Page<Buyers> result =  buyersRepository.findByIsDeleted(false, pageable);

        return result;    }

    @Override
    public Page<Buyers> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        Page<Buyers> result =  buyersRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Buyers> groOptional =  buyersRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Buyers not found ");
        }

        Buyers groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        buyersRepository.save(groExisting);


    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Buyers> groOptional =  buyersRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Buyers not found ");
        }
        Buyers groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        buyersRepository.save(groExisting);
    }

    @Override
    public Buyers findByBuyersName(String BuyersName) throws NotFoundException {
        return buyersRepository.findByBuyerName(BuyersName);    }

    @Override
    public Page<BuyersDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        List<BuyersDto>  result = buyersRepository.findByIsDeletedAndBuyerNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(buyersMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<BuyersDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<BuyersDto>  result = buyersRepository.findByBuyerNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(buyersMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }
}
