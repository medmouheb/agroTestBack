package com.agrotech.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotech.api.Repository.FermeRepository;
import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.dto.FermeDto;
import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FermeMapper;
import com.agrotech.api.model.Division;
import com.agrotech.api.model.Ferme;
import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.Sales;
import com.agrotech.api.services.FermeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {

	@Autowired
    private final FermeRepository fermeRepository;
	@Autowired
    private final FermeMapper fermeMapper;


    public Ferme save(Ferme entity) {
        return fermeRepository.save(entity);
    }


    @Override
    public FermeDto create(FermeDto dto) {
        return fermeMapper.toDto(
                save(
                        fermeMapper.toEntity(dto)
                )
        );
    }
    @Override
    public Page<Ferme> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Ferme>  result =  fermeRepository.findByIsDeletedAndNomContainingIgnoreCase(false,filter,pageable);
        return result;
        // return new PageImpl<>(result);
    }
    @Override
    public FermeDto update(String id, FermeDto dto) throws NotFoundException {
        Optional<Ferme> ferme = fermeRepository.findById(id);
        if (ferme.isEmpty()) {
            throw new NotFoundException("Fournisseur not found");
        }

        Ferme fermeExisting = ferme.get();
        fermeMapper.partialUpdate(fermeExisting, dto);
        return fermeMapper.toDto(
                save(fermeExisting)
        );
    }


    @Override
    public FermeDto findById(String id) throws NotFoundException {
        Optional<Ferme> optional = fermeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("ferme not found");
        }
        return fermeMapper.toDto(optional.get());
    }


    @Override
    public List<FermeDto> findAll() {
        return fermeRepository.findAll()
                .stream()
                .map(fermeMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public Page<FermeDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        return fermeRepository.findByNomContainingIgnoreCase(filter, pageable)
                .map(fermeMapper::toDto);
    }


    @Override
    public void delete(String id) throws NotFoundException {
        if (!fermeRepository.existsById(id)) {
            throw new NotFoundException("ferme not found");
        }
        fermeRepository.deleteById(id);
    }


    @Override
    public FermeDto findByCode(String code) throws NotFoundException {
        Optional<Ferme> optional = fermeRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Ferme not found");
        }
        return fermeMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Ferme> groOptional =  fermeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Currency not found ");
        }
        Ferme groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        fermeRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Ferme> groOptional =  fermeRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("CostCenter not found ");
        }
        Ferme groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        fermeRepository.save(groExisting);

    }

    @Override
    public Page<Ferme> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Ferme>  result =  fermeRepository.findByIsDeletedAndNomContainingIgnoreCase(true,filter,pageable);
        return result;
    }

//    @Override
//    public Page<FermeDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
//        Pageable pageable = PageRequest.of(
//                pageNumber,
//                pageSize
//        );
//        List<FermeDto>  result = fermeRepository.findByNameContainingIgnoreCase(filter, pageable)
//                .stream()
//                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
//                .map(fermeMapper::toDto)
//                .collect(Collectors.toList());
//
//        return new PageImpl<>(result);
//    }

}
