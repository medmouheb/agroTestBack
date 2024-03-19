package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.CropRepo;
import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CropMapper;
import com.agrotech.api.model.Crop;
import com.agrotech.api.services.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    @Autowired
    private CropRepo cropRepository ;

    @Autowired
    private CropMapper cropMapper ;

    public Crop save(Crop dto) {

        return cropRepository.save(dto);

    }


    @Override
    public CropDTO create(CropDTO dto) {
        return cropMapper.toDto(save(cropMapper.toEntity(dto)));

    }



    @Override
    public CropDTO update(String id, CropDTO dto) throws NotFoundException {

        Optional<Crop> camOptional =  cropRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }

        Crop campanyExisting = camOptional.get();
        cropMapper.partialUpdate(campanyExisting, dto);

        return cropMapper.toDto(save(campanyExisting));

    }

    @Override
    public CropDTO findById(String id) throws NotFoundException {
        Optional<Crop> campOptional = cropRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return cropMapper.toDto(campOptional.get());
    }

    @Override
    public List<CropDTO> findAll() {
        return cropRepository.findAll().stream()
                .map(cropMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!cropRepository.existsById(id)) {
            throw new NotFoundException("Crop not found ");
        }

        cropRepository.deleteById(id);


    }


    @Override
    public CropDTO findByCode(String code) throws NotFoundException {
        Optional<Crop> campOptional = cropRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return cropMapper.toDto(campOptional.get());
    }


    @Override
    public Page<Crop> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  cropRepository.findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<Crop> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  cropRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<Crop> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  cropRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<Crop> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  cropRepository.findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<CropDTO> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CropDTO>  result = cropRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .map(cropMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Crop> groOptional =  cropRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Crop groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        cropRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Crop> groOptional =  cropRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        Crop groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        cropRepository.save(groExisting);

    }

    @Override
    public Crop findByname(String name) throws NotFoundException {
        return cropRepository.findByName(name);
    }

    @Override
    public Page<CropDTO> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<CropDTO>  result = cropRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(cropMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }






}
