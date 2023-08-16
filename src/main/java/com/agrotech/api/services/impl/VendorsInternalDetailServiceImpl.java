package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorsInternalDetailRepository;
import com.agrotech.api.dto.VendorsInternalDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorsInternalDetailMapper;
import com.agrotech.api.model.VendorsInternalDetails;
import com.agrotech.api.services.VendorsInternalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorsInternalDetailServiceImpl implements VendorsInternalDetailService {

    @Autowired
    private VendorsInternalDetailRepository vendorsInternalDetailRepository ;

    @Autowired
    private VendorsInternalDetailMapper vendorsInternalDetailMapper ;


    public VendorsInternalDetails save(VendorsInternalDetails entity) {

        return vendorsInternalDetailRepository.save(entity);

    }

    @Override
    public VendorsInternalDetailsDto create(VendorsInternalDetailsDto dto) {
         return vendorsInternalDetailMapper.toDto(save(vendorsInternalDetailMapper.toEntity(dto))) ;

    }

    @Override
    public VendorsInternalDetailsDto update(String id, VendorsInternalDetailsDto dto) throws NotFoundException {
        Optional<VendorsInternalDetails> camOptional =  vendorsInternalDetailRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorsInternalDetail not found ");
        }

        VendorsInternalDetails campanyExisting = camOptional.get();
        vendorsInternalDetailMapper.partialUpdate(campanyExisting, dto);

        return vendorsInternalDetailMapper.toDto(save(campanyExisting));
    }

    @Override
    public VendorsInternalDetailsDto findById(String id) throws NotFoundException {
        Optional<VendorsInternalDetails> campOptional = vendorsInternalDetailRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorsInternalDetail not found ");
        }
        return vendorsInternalDetailMapper.toDto(campOptional.get());
    }

    @Override
    public List<VendorsInternalDetailsDto> findAll() {
        return vendorsInternalDetailRepository.findAll().stream()
                .map(vendorsInternalDetailMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorsInternalDetailsDto> findPage(int pageSize, int pageNumber, String filter) {
        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorsInternalDetailsDto>  result = vendorsInternalDetailRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsInternalDetailMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorsInternalDetailRepository.existsById(id)) {
            throw new NotFoundException("VendorsInternalDetail not found ");
        }

        vendorsInternalDetailRepository.deleteById(id);
    }



    @Override
    public Page<VendorsInternalDetailsDto> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        List<VendorsInternalDetailsDto> result =  vendorsInternalDetailRepository.findByIsDeletedAndIdContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsInternalDetailMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsInternalDetails> getpages(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorsInternalDetails> result =  vendorsInternalDetailRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorsInternalDetails> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        Page<VendorsInternalDetails> result =  vendorsInternalDetailRepository.findByIsDeleted(true, pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorsInternalDetails> groOptional =  vendorsInternalDetailRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorInternalDetail not found ");
        }
        VendorsInternalDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorsInternalDetailRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorsInternalDetails> groOptional =  vendorsInternalDetailRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Campany not found ");
        }
        VendorsInternalDetails groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorsInternalDetailRepository.save(groExisting);
    }

    @Override
    public Page<VendorsInternalDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorsInternalDetailsDto>  result = vendorsInternalDetailRepository.findByIsDeletedAndIdContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsInternalDetailMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsInternalDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorsInternalDetailsDto>  result = vendorsInternalDetailRepository.findByIdContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsInternalDetailMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
