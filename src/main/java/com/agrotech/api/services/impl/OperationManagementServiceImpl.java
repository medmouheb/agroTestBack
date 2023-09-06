package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.OperationManagementRepository;
import com.agrotech.api.dto.OperationManagementDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.OperationManagementMapper;
import com.agrotech.api.model.InventaireInitial;
import com.agrotech.api.model.OperationManagement;
import com.agrotech.api.services.OperationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationManagementServiceImpl implements OperationManagementService {

    @Autowired
    private OperationManagementRepository operationManagementRepository;

    @Autowired
    private OperationManagementMapper operationManagementMapper;


    public OperationManagement save(OperationManagement entity) {
        return operationManagementRepository.save(entity);
    }

    @Override
    public OperationManagementDto create(OperationManagementDto dto) {

        return operationManagementMapper.toDto(save(operationManagementMapper.toEntity(dto)));
    }

    @Override
    public OperationManagementDto update(String id, OperationManagementDto dto) throws NotFoundException {
        Optional<OperationManagement> camOptional =  operationManagementRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Operation Management not found ");
        }

        OperationManagement campanyExisting = camOptional.get();
        operationManagementMapper.partialUpdate(campanyExisting, dto);

        return operationManagementMapper.toDto(save(campanyExisting));
    }

    @Override
    public OperationManagementDto findById(String id) throws NotFoundException {
        Optional<OperationManagement> campOptional = operationManagementRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Operation Management not found ");
        }
        return operationManagementMapper.toDto(campOptional.get());    }

    @Override
    public List<OperationManagementDto> findAll() {
        return operationManagementRepository.findAll().stream()
                .map(operationManagementMapper::toDto)
                .collect(Collectors.toList());    }

    @Override
    public Page<OperationManagementDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<OperationManagementDto>  result =  operationManagementRepository.findByNomOperationContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(operationManagementMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public void delete(String id) throws NotFoundException {
        if (!operationManagementRepository.existsById(id)) {
            throw new NotFoundException("Operation Management not found ");
        }

        operationManagementRepository.deleteById(id);
    }

    @Override
    public OperationManagementDto findByOperationId(String operationId) throws NotFoundException {
        Optional<OperationManagement> campOptional = operationManagementRepository.findByOperationId(operationId);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Operation Management not found ");
        }
        return operationManagementMapper.toDto(campOptional.get());
    }

    @Override
    public Page<OperationManagementDto> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomOperation").ascending());
        List<OperationManagementDto> result =  operationManagementRepository.findByIsDeletedAndNomOperationContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(operationManagementMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<OperationManagement> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomOperation").ascending());
        Page<OperationManagement> result =  operationManagementRepository.findByIsDeletedAndNomOperationContainingIgnoreCase(false, filter,pageable);

        return result;    }

    @Override
    public Page<OperationManagement> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomOperation").ascending());
        Page<OperationManagement> result =  operationManagementRepository.findByIsDeletedAndNomOperationContainingIgnoreCase(true, filter,pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<OperationManagement> groOptional =  operationManagementRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("nomOperation not found ");
        }
        OperationManagement groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        operationManagementRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<OperationManagement> groOptional =  operationManagementRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("nomOperation not found ");
        }
        OperationManagement groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        operationManagementRepository.save(groExisting);
    }

    @Override
    public OperationManagement findByNomOperation(String nomOperation) throws NotFoundException {
        return operationManagementRepository.findByNomOperation(nomOperation);
    }

    @Override
    public Page<OperationManagementDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomOperation").ascending());
        List<OperationManagementDto>  result = operationManagementRepository.findByIsDeletedAndNomOperationContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(operationManagementMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<OperationManagementDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<OperationManagementDto>  result = operationManagementRepository.findByNomOperationContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(operationManagementMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public List<OperationManagement> findByNomDuChauffeurs() throws NotFoundException {

            return null;

    }
}
