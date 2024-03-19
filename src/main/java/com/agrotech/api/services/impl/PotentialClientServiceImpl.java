package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.BuyersRepository;
import com.agrotech.api.Repository.PotentialClientRepository;
import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.dto.PotentialClientDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BuyersMapper;
import com.agrotech.api.mapper.PotentialClientMapper;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.PotentialClient;
import com.agrotech.api.services.BuyersService;
import com.agrotech.api.services.PotentialClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PotentialClientServiceImpl  implements PotentialClientService {

    @Autowired
    private PotentialClientRepository potentialClientRepository ;

    @Autowired
    private PotentialClientMapper potentialClientMapper ;


    public PotentialClient save(PotentialClient dto) {
        return potentialClientRepository.save(dto);
    }

    @Override
    public PotentialClientDto create(PotentialClientDto dto) {
        System.out.println("yyyyyyyy");
        return potentialClientMapper.toDto(save(potentialClientMapper.toEntity(dto)));
    }

    @Override
    public PotentialClientDto update(String id, PotentialClientDto dto) throws NotFoundException {
        Optional<PotentialClient> camOptional =  potentialClientRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("PotentialClient not found ");
        }

        PotentialClient campanyExisting = camOptional.get();
        potentialClientMapper.partialUpdate(campanyExisting, dto);

        return potentialClientMapper.toDto(save(campanyExisting));    }

    @Override
    public PotentialClientDto findById(String id) throws NotFoundException {
        Optional<PotentialClient> campOptional = potentialClientRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("PotentialClient not found ");
        }
        return potentialClientMapper.toDto(campOptional.get());
    }


    @Override
    public List<PotentialClientDto> findAll() {
        return potentialClientRepository.findAll().stream()
                .map(potentialClientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PotentialClientDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<PotentialClientDto>  result = potentialClientRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(potentialClientMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!potentialClientRepository.existsById(id)) {
            throw new NotFoundException("PotentialClient not found ");
        }

        potentialClientRepository.deleteById(id);
    }

    @Override
    public PotentialClientDto findByBuyersCode(String BuyersCode) throws NotFoundException {
        Optional<PotentialClient> campOptional = potentialClientRepository.findByCode(BuyersCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("PotentialClient not found ");
        }
        return potentialClientMapper.toDto(campOptional.get());    }

    @Override
    public Page<PotentialClientDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        List<PotentialClientDto> result =  potentialClientRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(potentialClientMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<PotentialClient> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        Page<PotentialClient> result =  potentialClientRepository.findByIsDeleted(false, pageable);

        return result;    }

    @Override
    public Page<PotentialClient> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        Page<PotentialClient> result =  potentialClientRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<PotentialClient> groOptional =  potentialClientRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("PotentialClient not found ");
        }

        PotentialClient groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        potentialClientRepository.save(groExisting);


    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<PotentialClient> groOptional =  potentialClientRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("PotentialClient not found ");
        }
        PotentialClient groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        potentialClientRepository.save(groExisting);
    }

    @Override
    public PotentialClient findByBuyersName(String BuyersName) throws NotFoundException {
        return potentialClientRepository.findByName(BuyersName);    }

    @Override
    public Page<PotentialClientDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BuyersName").ascending());
        List<PotentialClientDto>  result = potentialClientRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(potentialClientMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<PotentialClientDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<PotentialClientDto>  result = potentialClientRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(potentialClientMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }
}

