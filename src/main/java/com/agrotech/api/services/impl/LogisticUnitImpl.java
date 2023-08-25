package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.Repository.LogisticUnitRepository;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CampanyMapper;
import com.agrotech.api.mapper.LogisticUnitMapper;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.services.LogisticUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LogisticUnitImpl implements LogisticUnitService {


    @Autowired
    private LogisticUnitRepository logisticUnitRepository;

    @Autowired
    private LogisticUnitMapper logisticUnitMapper ;


    public LogisticUnit save(LogisticUnit entity) {
        return logisticUnitRepository.save(entity);
    }


    @Override
    public LogisticUnitDto create(LogisticUnitDto dto) {
        return logisticUnitMapper.toDto(save(logisticUnitMapper.toEntity(dto)));
    }

    @Override
    public LogisticUnitDto update(String id, LogisticUnitDto dto) throws NotFoundException {
        Optional<LogisticUnit> camOptional =  logisticUnitRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("LogisticUnit not found ");
        }

        LogisticUnit campanyExisting = camOptional.get();
        logisticUnitMapper.partialUpdate(campanyExisting, dto);

        return logisticUnitMapper.toDto(save(campanyExisting));
    }

    @Override
    public LogisticUnitDto findById(String id) throws NotFoundException {
        Optional<LogisticUnit> campOptional = logisticUnitRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("LogisticUnit not found ");
        }
        return logisticUnitMapper.toDto(campOptional.get());
    }

    @Override
    public List<LogisticUnitDto> findAll() {
        return logisticUnitRepository.findAll().stream()
                .map(logisticUnitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LogisticUnitDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<LogisticUnitDto>  result =  logisticUnitRepository.findByLogisticNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(logisticUnitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!logisticUnitRepository.existsById(id)) {
            throw new NotFoundException("LogisticUnit not found ");
        }
        logisticUnitRepository.deleteById(id);
    }

    @Override
    public LogisticUnitDto findByLogisticCode(String LogisticCode) throws NotFoundException {
        Optional<LogisticUnit> campOptional = logisticUnitRepository.findByLogisticCode(LogisticCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("LogisticUnit not found ");
        }
        return logisticUnitMapper.toDto(campOptional.get());
    }

    @Override
    public Page<LogisticUnitDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("LogisticName").ascending());
        List<LogisticUnitDto> result =  logisticUnitRepository.findByIsDeletedAndLogisticNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(logisticUnitMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<LogisticUnit> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("LogisticName").ascending());
        Page<LogisticUnit> result =  logisticUnitRepository.findByIsDeletedAndLogisticNameContainingIgnoreCase(false, filter,pageable);

        return result;
    }

    @Override
    public Page<LogisticUnit> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("LogisticName").ascending());
        Page<LogisticUnit> result =  logisticUnitRepository.findByIsDeletedAndLogisticNameContainingIgnoreCase(true, filter,pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<LogisticUnit> groOptional =  logisticUnitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("LogisticName not found ");
        }
        LogisticUnit groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        logisticUnitRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<LogisticUnit> groOptional =  logisticUnitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("LogisticName not found ");
        }
        LogisticUnit groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        logisticUnitRepository.save(groExisting);
    }

    @Override
    public LogisticUnit findByLogisticName(String LogisticName) throws NotFoundException {

        return logisticUnitRepository.findByLogisticName(LogisticName);
    }

    @Override
    public Page<LogisticUnitDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<LogisticUnitDto>  result = logisticUnitRepository.findByIsDeletedAndLogisticNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(logisticUnitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public Page<LogisticUnitDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<LogisticUnitDto>  result = logisticUnitRepository.findByLogisticNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(logisticUnitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public List<LogisticUnitDto> findByLogisticCodes() throws NotFoundException {
        return logisticUnitRepository.findAll()
                .stream()
                .map(logisticUnitMapper::toDto)
                .collect(Collectors.toList());
    }

}
