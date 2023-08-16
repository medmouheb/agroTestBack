package com.agrotech.api.services.impl;
import java.util.List;
import java.util.Optional;

import com.agrotech.api.Repository.ReceptionRepository;
import com.agrotech.api.dto.ReceptionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.ReceptionMapper;
import com.agrotech.api.model.Reception;
import com.agrotech.api.services.ReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceptionServiceImpl implements ReceptionService {


    @Autowired
    private final ReceptionRepository receptionRepository;
    @Autowired
    private final ReceptionMapper receptionMapper;



    public Reception save(Reception entity) {

        return receptionRepository.save(entity);

    }

    @Override
    public ReceptionDto create(ReceptionDto dto) {
        return receptionMapper.toDto(save(receptionMapper.toEntity(dto)));
    }

    @Override
    public ReceptionDto update(String id, ReceptionDto dto) throws NotFoundException {
        Optional<Reception> camOptional =  receptionRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Reception not found ");
        }

        Reception receptionExisting = camOptional.get();
        receptionMapper.partialUpdate(receptionExisting, dto);

        return receptionMapper.toDto(save(receptionExisting));
    }

    @Override
    public ReceptionDto findById(String id) throws NotFoundException {
        Optional<Reception> campOptional = receptionRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Reception not found ");
        }
        return receptionMapper.toDto(campOptional.get());
    }

    @Override
    public Page<ReceptionDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<ReceptionDto> result =  receptionRepository.findByIsDeletedAndIdContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(receptionMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<Reception> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<Reception> result =  receptionRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<Reception> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Reception> result =  receptionRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Reception> groOptional =  receptionRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Reception groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        receptionRepository.save(groExisting);


    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Reception> groOptional =  receptionRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        Reception groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        receptionRepository.save(groExisting);
    }

    @Override
    public Page<ReceptionDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<ReceptionDto>  result = receptionRepository.findByIsDeletedAndIdContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(receptionMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<ReceptionDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<ReceptionDto>  result = receptionRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(receptionMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public List<ReceptionDto> findAll() {
        return receptionRepository.findAll().stream()
                .map(receptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ReceptionDto> findPage(int pageSize, int pageNumber, String filter) {
        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ReceptionDto>  result = receptionRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(receptionMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!receptionRepository.existsById(id)) {
            throw new NotFoundException("Reception not found ");
        }

        receptionRepository.deleteById(id);
    }
}
