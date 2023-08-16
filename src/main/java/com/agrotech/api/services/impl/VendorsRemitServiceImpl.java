package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorsRemitRepository;
import com.agrotech.api.dto.VendorsRemitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorsRemitMapper;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.VendorsRemit;
import com.agrotech.api.services.VendorsRemitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorsRemitServiceImpl implements VendorsRemitService {

    @Autowired
    private VendorsRemitRepository vendorsRemitRepository ;

    @Autowired
    private VendorsRemitMapper vendorsRemitMapper ;

    public VendorsRemit save(VendorsRemit entity) {
        return vendorsRemitRepository.save(entity);
    }


    @Override
    public VendorsRemitDto create(VendorsRemitDto dto) {
        return vendorsRemitMapper.toDto(save(vendorsRemitMapper.toEntity(dto))) ;

    }

    @Override
    public VendorsRemitDto update(String id, VendorsRemitDto dto) throws NotFoundException {
        Optional<VendorsRemit> camOptional =  vendorsRemitRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorsRemit not found ");
        }

        VendorsRemit campanyExisting = camOptional.get();
        vendorsRemitMapper.partialUpdate(campanyExisting, dto);

        return vendorsRemitMapper.toDto(save(campanyExisting));

    }

    @Override
    public List<VendorsRemitDto> findAll() {
        return vendorsRemitRepository.findAll()
                .stream().map(vendorsRemitMapper::toDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Page<VendorsRemitDto> findPage(int pageSize, int pageNumber, String filter) {
        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorsRemitDto>  result = vendorsRemitRepository.findByRemitToNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsRemitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorsRemitRepository.existsById(id)) {
            throw new NotFoundException("VendorRemit not found ");
        }

        vendorsRemitRepository.deleteById(id);

    }

    @Override
    public VendorsRemitDto findById(String id) throws NotFoundException {
        Optional<VendorsRemit> campOptional = vendorsRemitRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorsRemit not found ");
        }
        return vendorsRemitMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorsRemitDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("VendorRemit").ascending());
        List<VendorsRemitDto> result =  vendorsRemitRepository.findByIsDeletedAndRemitToNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsRemitMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsRemit> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("VendorRemit").ascending());
        Page<VendorsRemit> result =  vendorsRemitRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorsRemit> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<VendorsRemit> result =  vendorsRemitRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorsRemit> groOptional =  vendorsRemitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorsRemit not found ");
        }
        VendorsRemit groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorsRemitRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorsRemit> groOptional =  vendorsRemitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorsRemit not found ");
        }
        VendorsRemit groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorsRemitRepository.save(groExisting);
    }

    @Override
    public VendorsRemit findByRemitToName(String RemitToName) throws NotFoundException {

        return vendorsRemitRepository.findByRemitToName(RemitToName);

    }

    @Override
    public Page<VendorsRemitDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorsRemitDto>  result = vendorsRemitRepository.findByIsDeletedAndRemitToNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsRemitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsRemitDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorsRemitDto>  result = vendorsRemitRepository.findByRemitToNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsRemitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
