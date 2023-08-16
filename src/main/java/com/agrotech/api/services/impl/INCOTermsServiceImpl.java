package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.INCOTermsRepository;
import com.agrotech.api.dto.INCOTermsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.INCOTermsMapper;
import com.agrotech.api.model.INCOTerms;
import com.agrotech.api.services.INCOTermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class INCOTermsServiceImpl implements INCOTermsService {

    @Autowired
    private INCOTermsRepository INCOTermsRepository;

    @Autowired
    private INCOTermsMapper INCOTermsMapper ;


    public INCOTerms save(INCOTerms entity) {
        return INCOTermsRepository.save(entity);
    }

    @Override
    public INCOTermsDto create(INCOTermsDto dto) {
        return INCOTermsMapper.toDto(save(INCOTermsMapper.toEntity(dto)));
    }

    @Override
    public INCOTermsDto update(String id, INCOTermsDto dto) throws NotFoundException {
        Optional<INCOTerms> camOptional =  INCOTermsRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("INCOTerms not found ");
        }

        INCOTerms campanyExisting = camOptional.get();
        INCOTermsMapper.partialUpdate(campanyExisting, dto);

        return INCOTermsMapper.toDto(save(campanyExisting));


    }

    @Override
    public INCOTermsDto findById(String id) throws NotFoundException {
        Optional<INCOTerms> campOptional = INCOTermsRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("INCOTerms not found ");
        }
        return INCOTermsMapper.toDto(campOptional.get());    }

    @Override
    public List<INCOTermsDto> findAll() {
        return INCOTermsRepository.findAll().stream()
                .map(INCOTermsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<INCOTermsDto> findPage(int pageSize, int pageNumber, String filter) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!INCOTermsRepository.existsById(id)) {
            throw new NotFoundException("INCOTerms not found ");
        }

        INCOTermsRepository.deleteById(id);


    }

    @Override
    public INCOTermsDto findByINCOTermCode(String INCOTermCode) throws NotFoundException {
        Optional<INCOTerms> campOptional = INCOTermsRepository.findByINCOTermCode(INCOTermCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("INCOTerms not found ");
        }
        return INCOTermsMapper.toDto(campOptional.get());    }

    @Override
    public Page<INCOTermsDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("INCOTermName").ascending());
        List<INCOTermsDto> result =  INCOTermsRepository.findByIsDeletedAndINCOTermNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(INCOTermsMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<INCOTerms> getpages(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("INCOTermName").ascending());
        Page<INCOTerms> result =  INCOTermsRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<INCOTerms> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("INCOTermName").ascending());
        Page<INCOTerms> result =  INCOTermsRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<INCOTerms> groOptional =  INCOTermsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("INCOTerms not found ");
        }
        INCOTerms groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        INCOTermsRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<INCOTerms> groOptional =  INCOTermsRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        INCOTerms groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        INCOTermsRepository.save(groExisting);
    }

    @Override
    public INCOTerms findByINCOTermName(String INCOTermName) throws NotFoundException {
        return INCOTermsRepository.findByINCOTermName(INCOTermName);
    }

    @Override
    public Page<INCOTermsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("INCOTermName").ascending());
        List<INCOTermsDto>  result = INCOTermsRepository.findByIsDeletedAndINCOTermNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(INCOTermsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<INCOTermsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<INCOTermsDto>  result = INCOTermsRepository.findByINCOTermNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(INCOTermsMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
