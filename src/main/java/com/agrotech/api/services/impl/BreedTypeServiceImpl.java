package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.BreedTypeRepository;
import com.agrotech.api.dto.BreedTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.BreedTypeMapper;
import com.agrotech.api.model.BreedType;
import com.agrotech.api.services.BreedTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BreedTypeServiceImpl implements BreedTypeService {

    private final BreedTypeRepository breedTypeRepository;
    private final BreedTypeMapper breedTypeMapper;

    private BreedType save(BreedType toEntity) {

        return breedTypeRepository.save(toEntity);
    }

    @Override
    public BreedTypeDto create(BreedTypeDto dto) {

        return breedTypeMapper.toDto(save(breedTypeMapper.toEntity(dto)));
    }




    @Override
    public BreedTypeDto update(String id, BreedTypeDto dto) throws NotFoundException {
        Optional<BreedType> breedTypeOptional = breedTypeRepository.findById(id);
        if (breedTypeOptional.isEmpty()) {
            throw new NotFoundException("BreedType not found ");
        }
        BreedType breedTypeExisting = breedTypeOptional.get();
        breedTypeMapper.partialUpdate(breedTypeExisting, dto);
        return breedTypeMapper.toDto(save(breedTypeExisting));
    }



    @Override
    public BreedTypeDto findById(String id) throws NotFoundException {
        Optional<BreedType> breedTypeOptional = breedTypeRepository.findById(id);
        if(breedTypeOptional.isEmpty()) {
            throw new NotFoundException("BreedType not found");
        }
        return breedTypeMapper.toDto(breedTypeOptional.get());
    }

    @Override
    public List<BreedTypeDto> findAll() {
        return breedTypeRepository.findAll().stream()
                .map(breedTypeMapper::toDto)
                .collect(Collectors.toList());
    }



    @Override
    public Page<BreedTypeDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<BreedTypeDto>  result = breedTypeRepository.findByBreedTypeNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(breedTypeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!breedTypeRepository.existsById(id)) {
            throw new NotFoundException("BreedType not found ");
        }

        breedTypeRepository.deleteById(id);
    }

    @Override
    public BreedTypeDto findByBreedTypeCode(String BreedTypeCode) throws NotFoundException {
        Optional<BreedType> breedOptional = breedTypeRepository.findByBreedTypeCode(BreedTypeCode);
        if(breedOptional.isEmpty()) {
            throw new NotFoundException("BreedType not found ");
        }
        return breedTypeMapper.toDto(breedOptional.get());
    }

    @Override
    public Page<BreedTypeDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedTypeName").ascending());
        List<BreedTypeDto> result =  breedTypeRepository.findByIsDeletedAndBreedTypeNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(breedTypeMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<BreedType> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedTypeName").ascending());
        Page<BreedType> result =  breedTypeRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<BreedType> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedTypeName").ascending());
        Page<BreedType> result =  breedTypeRepository.findByIsDeleted(true, pageable);

        return result;
    }


    @Override
    public void archive(String id) throws NotFoundException {
        Optional<BreedType> groOptional =  breedTypeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("BreedType not found ");
        }
        BreedType groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        breedTypeRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<BreedType> groOptional =  breedTypeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        BreedType groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        breedTypeRepository.save(groExisting);
    }

    @Override
    public BreedType findByBreedTypeName(String BreedTypeName) throws NotFoundException {
        return breedTypeRepository.findByBreedTypeName(BreedTypeName);
    }

    @Override
    public Page<BreedTypeDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("BreedTypeName").ascending());
        List<BreedTypeDto>  result = breedTypeRepository.findByIsDeletedAndBreedTypeNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(breedTypeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<BreedTypeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<BreedTypeDto>  result = breedTypeRepository.findByBreedTypeNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(breedTypeMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }


}
