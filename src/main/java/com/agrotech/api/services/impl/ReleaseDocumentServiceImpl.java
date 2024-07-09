package com.agrotech.api.services.impl;


import com.agrotech.api.Repository.ReleaseDocumentRepository;
import com.agrotech.api.dto.ReleaseDocumentDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.ReleaseDocumentMapper;
import com.agrotech.api.model.ReleaseDocument;
import com.agrotech.api.services.ReleaseDocumentService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReleaseDocumentServiceImpl  implements ReleaseDocumentService {

    @Autowired
    private ReleaseDocumentRepository releaseDocumentRepository ;

    @Autowired
    private ReleaseDocumentMapper releaseDocumentMapper ;



    public ReleaseDocument save(ReleaseDocument dto) {

        return releaseDocumentRepository.save(dto);

    }


    @Override
    public ReleaseDocumentDto create(ReleaseDocumentDto dto) throws DocumentException, FileNotFoundException {

        return releaseDocumentMapper.toDto(save(releaseDocumentMapper.toEntity(dto)));

    }



    @Override
    public ReleaseDocumentDto update(String id, ReleaseDocumentDto dto) throws NotFoundException {

        Optional<ReleaseDocument> camOptional =  releaseDocumentRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("ReleaseDocument not found ");
        }

        ReleaseDocument campanyExisting = camOptional.get();
        releaseDocumentMapper.partialUpdate(campanyExisting, dto);

        return releaseDocumentMapper.toDto(save(campanyExisting));

    }

    @Override
    public ReleaseDocumentDto findById(String id) throws NotFoundException {
        Optional<ReleaseDocument> campOptional = releaseDocumentRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("ReleaseDocument not found ");
        }
        return releaseDocumentMapper.toDto(campOptional.get());
    }

    @Override
    public List<ReleaseDocumentDto> findAll() {
        return releaseDocumentRepository.findAll().stream()
                .map(releaseDocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!releaseDocumentRepository.existsById(id)) {
            throw new NotFoundException("ReleaseDocument not found ");
        }

        releaseDocumentRepository.deleteById(id);


    }


    @Override
    public ReleaseDocumentDto findByCode(String code) throws NotFoundException {
        Optional<ReleaseDocument> campOptional = releaseDocumentRepository.findByCode(code);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return releaseDocumentMapper.toDto(campOptional.get());
    }


    @Override
    public Page<ReleaseDocument> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  releaseDocumentRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<ReleaseDocument> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  releaseDocumentRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<ReleaseDocument> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  releaseDocumentRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<ReleaseDocument> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  releaseDocumentRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<ReleaseDocumentDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ReleaseDocumentDto>  result = releaseDocumentRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(releaseDocumentMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<ReleaseDocument> groOptional =  releaseDocumentRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        ReleaseDocument groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        releaseDocumentRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<ReleaseDocument> groOptional =  releaseDocumentRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        ReleaseDocument groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        releaseDocumentRepository.save(groExisting);

    }

}

