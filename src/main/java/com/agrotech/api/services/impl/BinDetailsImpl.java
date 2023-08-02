package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.BinDetailsRepository;

import com.agrotech.api.dto.BinDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BinDetailsMapper;
import com.agrotech.api.model.BinDetails;
import com.agrotech.api.services.BinDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BinDetailsImpl implements BinDetailsService {

    @Autowired
    private BinDetailsRepository  binDetailsRepository ;
    @Autowired
    private BinDetailsMapper binDetailsMapper ;



    public BinDetails save(BinDetails dto) {

        return binDetailsRepository.save(dto);

    }

    @Override
    public BinDetailsDto create(BinDetailsDto dto) {
    return binDetailsMapper.toDto(save(binDetailsMapper.toEntity(dto)));

    }

    @Override
    public BinDetailsDto update(String s, BinDetailsDto dto) throws NotFoundException {

        Optional<BinDetails> binOptional =  binDetailsRepository.findById(s);
        if(binOptional.isEmpty()) {
            throw new NotFoundException("binDetails not found ");
        }

        BinDetails binExisting = binOptional.get();
        binDetailsMapper.partialUpdate(binExisting, dto);

        return binDetailsMapper.toDto(save(binExisting));

    }

    @Override
    public BinDetailsDto findById(String s) throws NotFoundException {
        Optional<BinDetails> binOptional = binDetailsRepository.findById(s);
        if(binOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return binDetailsMapper.toDto(binOptional.get());
    }

    @Override
    public List<BinDetailsDto> findAll() {
        return binDetailsRepository.findAll().stream()
                .map(binDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BinDetailsDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BinDetailsDto>  result = binDetailsRepository.findByCapacityContainingIgnoreCase(Double.valueOf(filter), pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(binDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String s) throws NotFoundException {
        if(!binDetailsRepository.existsById(s)) {
            throw new NotFoundException("BinDetails not found ");
        }
        binDetailsRepository.deleteById(s);
    }

    @Override
    public BinDetailsDto findByBin(Number bin) throws NotFoundException {
        Optional<BinDetails> binOptional = binDetailsRepository.findByBin(bin);
        if(binOptional.isEmpty()) {
            throw new NotFoundException("binOptional not found ");
        }
        return binDetailsMapper.toDto(binOptional.get());
    }

    @Override
    public Page<BinDetailsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bin").ascending());
        List<BinDetailsDto> result =  binDetailsRepository.findByIsDeletedAndCapacityContainingIgnoreCase(false, Double.valueOf(filter), pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(binDetailsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<BinDetails> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bin").ascending());
        Page<BinDetails> result =  binDetailsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<BinDetails> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bin").ascending());
        Page<BinDetails> result =  binDetailsRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<BinDetails> groOptional =  binDetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        BinDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        binDetailsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<BinDetails> groOptional =  binDetailsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("binDetails not found ");
        }
        BinDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        binDetailsRepository.save(groExisting);
    }

    @Override
    public BinDetails findByCapacity(Double capacity) throws NotFoundException {
        return binDetailsRepository.findByCapacity(capacity);

    }

    @Override
    public Page<BinDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bin").ascending());
        List<BinDetailsDto>  result = binDetailsRepository.findByIsDeletedAndCapacityContainingIgnoreCase(true, Double.valueOf(filter), pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(binDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<BinDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<BinDetailsDto>  result = binDetailsRepository.findByIsDeletedAndCapacityContainingIgnoreCase(
                true,
                Double.valueOf(filter),
                pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(binDetailsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
