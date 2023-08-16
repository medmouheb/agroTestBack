package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.BreedCodeRepository;
import com.agrotech.api.dto.BreedCodeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BreedCodeMapper;
import com.agrotech.api.model.BreedCode;
import com.agrotech.api.services.BreedCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BreedCodeServiceImpl implements BreedCodeService {

    @Autowired
    private BreedCodeRepository breedCodeRepository ;

    @Autowired
    private BreedCodeMapper breedCodeMapper ;


    public BreedCode save(BreedCode entity) {
        return breedCodeRepository.save(entity);

    }

    @Override
    public BreedCodeDto create(BreedCodeDto dto) {
        return breedCodeMapper.toDto(save(breedCodeMapper.toEntity(dto)));

    }

    @Override
    public BreedCodeDto update(String id, BreedCodeDto dto) throws NotFoundException {

        Optional<BreedCode> camOptional =  breedCodeRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("BreedCode not found ");
        }

        BreedCode campanyExisting = camOptional.get();
        breedCodeMapper.partialUpdate(campanyExisting, dto);

        return breedCodeMapper.toDto(save(campanyExisting));
    }

    @Override
    public BreedCodeDto findById(String id) throws NotFoundException {
        Optional<BreedCode> campOptional = breedCodeRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("BreedCode not found ");
        }
        return breedCodeMapper.toDto(campOptional.get());
    }

    @Override
    public List<BreedCodeDto> findAll() {

        return breedCodeRepository.findAll().stream().map(breedCodeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<BreedCodeDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!breedCodeRepository.existsById(id)) {
            throw new NotFoundException("BreedCode not found ");
        }

        breedCodeRepository.deleteById(id);

    }

    @Override
    public BreedCodeDto findByBreedCode(String BreedCode) throws NotFoundException {
        Optional<BreedCode> campOptional = breedCodeRepository.findByBreedCode(BreedCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        return breedCodeMapper.toDto(campOptional.get());
    }

    @Override
    public Page<BreedCodeDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedCodeName").ascending());
        List<BreedCodeDto> result =  breedCodeRepository.findByIsDeletedAndBreedCodeNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(breedCodeMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<BreedCode> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedCodeName").ascending());
        Page<BreedCode> result =  breedCodeRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<BreedCode> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedCodeName").ascending());
        Page<BreedCode> result =  breedCodeRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {

        Optional<BreedCode> groOptional =  breedCodeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("BreedCode not found ");
        }
        BreedCode groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        breedCodeRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<BreedCode> groOptional =  breedCodeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        BreedCode groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        breedCodeRepository.save(groExisting);
    }

    @Override
    public BreedCode findByBreedCodeName(String BreedCodeName) {
        return breedCodeRepository.findByBreedCodeName(BreedCodeName);    }

    @Override
    public Page<BreedCodeDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedCodeName").ascending());
        List<BreedCodeDto>  result = breedCodeRepository.findByIsDeletedAndBreedCodeNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(breedCodeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<BreedCodeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<BreedCodeDto>  result = breedCodeRepository.findByBreedCodeNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(breedCodeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
