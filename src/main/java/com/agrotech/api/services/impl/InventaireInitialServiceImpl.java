package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.InventaireInitialRepository;
import com.agrotech.api.dto.InventaireInitialDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.InventaireInitialMapper;
import com.agrotech.api.model.InventaireInitial;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.services.InventaireInitialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class InventaireInitialServiceImpl implements InventaireInitialService {

    @Autowired
    private InventaireInitialRepository inventaireInitialRepository;

    @Autowired
    private InventaireInitialMapper inventaireInitialMapper;


    public InventaireInitial save(InventaireInitial entity) {
        return inventaireInitialRepository.save(entity);
    }

    @Override
    public InventaireInitialDto create(InventaireInitialDto dto) {
        return inventaireInitialMapper.toDto(save(inventaireInitialMapper.toEntity(dto)));

    }

    @Override
    public InventaireInitialDto update(String id, InventaireInitialDto dto) throws NotFoundException {
        Optional<InventaireInitial> camOptional =  inventaireInitialRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Inventaire Initial not found ");
        }

        InventaireInitial campanyExisting = camOptional.get();
        inventaireInitialMapper.partialUpdate(campanyExisting, dto);

        return inventaireInitialMapper.toDto(save(campanyExisting));    }

    @Override
    public InventaireInitialDto findById(String id) throws NotFoundException {
        Optional<InventaireInitial> campOptional = inventaireInitialRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Inventaire Initial not found ");
        }
        return inventaireInitialMapper.toDto(campOptional.get());
    }

    @Override
    public List<InventaireInitialDto> findAll() {
        return inventaireInitialRepository.findAll().stream()
                .map(inventaireInitialMapper::toDto)
                .collect(Collectors.toList());    }

    @Override
    public Page<InventaireInitialDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<InventaireInitialDto>  result =  inventaireInitialRepository.findByNomDuProduitContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(inventaireInitialMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!inventaireInitialRepository.existsById(id)) {
            throw new NotFoundException("Inventaire Unitial not found ");
        }
        inventaireInitialRepository.deleteById(id);
    }

    @Override
    public InventaireInitialDto findByCodeProduit(String codeProduit) throws NotFoundException {
        Optional<InventaireInitial> campOptional = inventaireInitialRepository.findByCodeProduit(codeProduit);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("CodeProduit not found ");
        }
        return inventaireInitialMapper.toDto(campOptional.get());    }

    @Override
    public Page<InventaireInitialDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        List<InventaireInitialDto> result =  inventaireInitialRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(inventaireInitialMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);    }

    @Override
    public Page<InventaireInitial> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        Page<InventaireInitial> result =  inventaireInitialRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(false, filter,pageable);

        return result;
    }

    @Override
    public Page<InventaireInitial> getpagesarchive(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        Page<InventaireInitial> result =  inventaireInitialRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(true, filter,pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<InventaireInitial> groOptional =  inventaireInitialRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Inventaire Initial not found ");
        }
        InventaireInitial groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        inventaireInitialRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<InventaireInitial> groOptional =  inventaireInitialRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Inventaire Initial not found ");
        }
        InventaireInitial groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        inventaireInitialRepository.save(groExisting);
    }

    @Override
    public InventaireInitial findByNomDuProduit(String nomDuProduit) throws NotFoundException {
        return inventaireInitialRepository.findByNomDuProduit(nomDuProduit);    }

    @Override
    public Page<InventaireInitialDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        List<InventaireInitialDto>  result = inventaireInitialRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(inventaireInitialMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);     }

    @Override
    public Page<InventaireInitialDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<InventaireInitialDto>  result = inventaireInitialRepository.findByNomDuProduitContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(inventaireInitialMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
