package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.VendorsContactInformationRepository;
import com.agrotech.api.dto.VendorsContactInformationDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.VendorsContactInformationMapper;
import com.agrotech.api.model.VendorsContactInformation;
import com.agrotech.api.services.VendorsContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorsContactInformationServiceImpl implements VendorsContactInformationService {


    @Autowired
    private VendorsContactInformationRepository vendorsContactInformationRepository ;

    @Autowired
    private VendorsContactInformationMapper vendorsContactInformationMapper ;

    public VendorsContactInformation save(VendorsContactInformation entity) {

        return vendorsContactInformationRepository.save(entity);

    }



    @Override
    public VendorsContactInformationDto create(VendorsContactInformationDto dto) {
        return vendorsContactInformationMapper.toDto(save(vendorsContactInformationMapper.toEntity(dto))) ;

    }

    @Override
    public VendorsContactInformationDto update(String id, VendorsContactInformationDto dto) throws NotFoundException {
        Optional<VendorsContactInformation> camOptional =  vendorsContactInformationRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("VendorsContactInformation not found ");
        }

        VendorsContactInformation campanyExisting = camOptional.get();
        vendorsContactInformationMapper.partialUpdate(campanyExisting, dto);

        return vendorsContactInformationMapper.toDto(save(campanyExisting));

    }

    @Override
    public VendorsContactInformationDto findById(String id) throws NotFoundException {
        Optional<VendorsContactInformation> campOptional = vendorsContactInformationRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorsContactInformation not found ");
        }
        return vendorsContactInformationMapper.toDto(campOptional.get());
    }

    @Override
    public List<VendorsContactInformationDto> findAll() {
        return vendorsContactInformationRepository.findAll().stream()
                .map(vendorsContactInformationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VendorsContactInformationDto> findPage(int pageSize, int pageNumber, String filter) {

        // Pageable pageable = PageRequest.of(
        // 		pageNumber,
        // 		pageSize
        // );
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<VendorsContactInformationDto>  result = vendorsContactInformationRepository.findByLabelNameContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsContactInformationMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!vendorsContactInformationRepository.existsById(id)) {
            throw new NotFoundException("VendorsContactInformation not found ");
        }

        vendorsContactInformationRepository.deleteById(id);

    }

    @Override
    public VendorsContactInformationDto findByLabelCode(String LabelCode) throws NotFoundException {
        Optional<VendorsContactInformation> campOptional = vendorsContactInformationRepository.findByLabelCode(LabelCode);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("VendorsContact not found ");
        }
        return vendorsContactInformationMapper.toDto(campOptional.get());
    }

    @Override
    public Page<VendorsContactInformationDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("LabelName").ascending());
        List<VendorsContactInformationDto> result =  vendorsContactInformationRepository.findByIsDeletedAndLabelNameContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(vendorsContactInformationMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<VendorsContactInformation> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("LabelName").ascending());
        Page<VendorsContactInformation> result =  vendorsContactInformationRepository.findByIsDeleted(false, pageable);

        return result;
    }

    @Override
    public Page<VendorsContactInformation> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("LabelName").ascending());
        Page<VendorsContactInformation> result =  vendorsContactInformationRepository.findByIsDeleted(true, pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<VendorsContactInformation> groOptional =  vendorsContactInformationRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorsContactInformation not found ");
        }
        VendorsContactInformation groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        vendorsContactInformationRepository.save(groExisting);


    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<VendorsContactInformation> groOptional =  vendorsContactInformationRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("VendorsContactInformation not found ");
        }
        VendorsContactInformation groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        vendorsContactInformationRepository.save(groExisting);

    }

    @Override
    public VendorsContactInformation findByLabelName(String LabelName) throws NotFoundException {
        return vendorsContactInformationRepository.findByLabelName(LabelName);
    }

    @Override
    public Page<VendorsContactInformationDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        List<VendorsContactInformationDto>  result = vendorsContactInformationRepository.findByIsDeletedAndLabelNameContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsContactInformationMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }


    @Override
    public Page<VendorsContactInformationDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<VendorsContactInformationDto>  result = vendorsContactInformationRepository.findByLabelNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(vendorsContactInformationMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
