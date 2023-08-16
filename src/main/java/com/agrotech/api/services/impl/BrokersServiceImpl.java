package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.BrokersRepository;
import com.agrotech.api.dto.BrokersDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BrokersMapper;
import com.agrotech.api.model.Brokers;
import com.agrotech.api.services.BrokersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrokersServiceImpl implements BrokersService {


    @Autowired
    private BrokersRepository brokersRepository;
    @Autowired
    private BrokersMapper brokersMapper;


    private Brokers save(Brokers toEntity) {

        return brokersRepository.save(toEntity);
    }

    @Override
    public BrokersDto create(BrokersDto dto) {

            return brokersMapper.toDto(save(brokersMapper.toEntity(dto)));
    }

    @Override
    public BrokersDto update(String id, BrokersDto dto) throws NotFoundException {
        Optional<Brokers> camOptional =  brokersRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Brokers not found ");
        }

        Brokers campanyExisting = camOptional.get();
        brokersMapper.partialUpdate(campanyExisting, dto);

        return brokersMapper.toDto(save(campanyExisting));      }

    @Override
    public BrokersDto findById(String id) throws NotFoundException {
        Optional<Brokers> campOptional = brokersRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Brokers not found ");
        }
        return brokersMapper.toDto(campOptional.get());    }

    @Override
    public List<BrokersDto> findAll() {
        return brokersRepository.findAll().stream()
                .map(brokersMapper::toDto)
                .collect(Collectors.toList());    }

    @Override
    public Page<BrokersDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BrokersDto>  result = brokersRepository.findByBrokerNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(brokersMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!brokersRepository.existsById(id)) {
            throw new NotFoundException("Brokers not found ");
        }

        brokersRepository.deleteById(id);
    }

    @Override
    public BrokersDto findByBrokerCode(String BrokerCode) throws NotFoundException {
        Optional<Brokers> campOptional = brokersRepository.findByBrokerCode(BrokerCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Brokers not found ");
        }
        return brokersMapper.toDto(campOptional.get());     }

    @Override
    public Page<BrokersDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BrokersName").ascending());
        List<BrokersDto> result =  brokersRepository.findByIsDeletedAndBrokerNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(brokersMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);    }

    @Override
    public Page<Brokers> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BrokersName").ascending());
        Page<Brokers> result =  brokersRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<Brokers> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        Page<Brokers> result =  brokersRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Brokers> groOptional =  brokersRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("BuyersName not found ");
    }
        Brokers groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        brokersRepository.save(groExisting);


    }
    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Brokers> groOptional =  brokersRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Brokers not found ");
        }
        Brokers groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        brokersRepository.save(groExisting);
    }

    @Override
    public Brokers findByBrokerName(String BrokerName) throws NotFoundException {
        return brokersRepository.findByBrokerName(BrokerName);
    }

    @Override
    public Page<BrokersDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        List<BrokersDto>  result = brokersRepository.findByIsDeletedAndBrokerNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(brokersMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<BrokersDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<BrokersDto>  result = brokersRepository.findByBrokerNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(brokersMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);     }
}
