package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.UtilisationDuProduitRepository;
import com.agrotech.api.dto.UtilisationDuProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.UtilisationDuProduitMapper;
import com.agrotech.api.model.UtilisationDuProduit;
import com.agrotech.api.services.UtilisationDuProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UtilisationDuProduitServiceImpl implements UtilisationDuProduitService {

    @Autowired
    private UtilisationDuProduitRepository utilisationDuProduitRepository;


    @Autowired
    private UtilisationDuProduitMapper utilisationDuProduitMapper;

    //save
    public UtilisationDuProduit save(UtilisationDuProduit utilisationDuProduit){
        return utilisationDuProduitRepository.save(utilisationDuProduit);
    }




    @Override
    public UtilisationDuProduitDto create(UtilisationDuProduitDto dto) {

        return utilisationDuProduitMapper.toDto(
                save(
                        utilisationDuProduitMapper.toEntity(dto)
                )
        );



    }

    @Override
    public UtilisationDuProduitDto update(String id, UtilisationDuProduitDto dto) throws NotFoundException {
        Optional<UtilisationDuProduit> camOptional =  utilisationDuProduitRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Utilisation Du Produit not found ");
        }

        UtilisationDuProduit campanyExisting = camOptional.get();
        utilisationDuProduitMapper.partialUpdate(campanyExisting, dto);

        return utilisationDuProduitMapper.toDto(save(campanyExisting));    }

    @Override
    public UtilisationDuProduitDto findById(String id) throws NotFoundException {
        Optional<UtilisationDuProduit> campOptional = utilisationDuProduitRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Utilisation Du Produit  not found ");
        }
        return utilisationDuProduitMapper.toDto(campOptional.get());    }

    @Override
    public List<UtilisationDuProduitDto> findAll() {
        return utilisationDuProduitRepository.findAll().stream()
                .map(utilisationDuProduitMapper::toDto)
                .collect(Collectors.toList());    }

    @Override
    public Page<UtilisationDuProduitDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<UtilisationDuProduitDto>  result =  utilisationDuProduitRepository.findByNomDuProduitContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(utilisationDuProduitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!utilisationDuProduitRepository.existsById(id)) {
            throw new NotFoundException("Utilisation Du Produit  not found ");
        }
        utilisationDuProduitRepository.deleteById(id);
    }

    @Override
    public UtilisationDuProduitDto findByCodeProduit(String codeProduit) throws NotFoundException {
        Optional<UtilisationDuProduit> campOptional = utilisationDuProduitRepository.findByNumeroDeLot(codeProduit);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Utilisation Du Produit  not found ");
        }
        return utilisationDuProduitMapper.toDto(campOptional.get());
    }

    @Override
    public Page<UtilisationDuProduitDto> findPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        List<UtilisationDuProduitDto> result =  utilisationDuProduitRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(utilisationDuProduitMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);    }

    @Override
    public Page<UtilisationDuProduit> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        Page<UtilisationDuProduit> result =  utilisationDuProduitRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(false, filter,pageable);

        return result;    }

    @Override
    public Page<UtilisationDuProduit> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        Page<UtilisationDuProduit> result =  utilisationDuProduitRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(true, filter,pageable);

        return result;    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<UtilisationDuProduit> groOptional =  utilisationDuProduitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Utilisation Du Produit not found ");
        }
        UtilisationDuProduit groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        utilisationDuProduitRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<UtilisationDuProduit> groOptional =  utilisationDuProduitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Utilisation Du Produit not found ");
        }
        UtilisationDuProduit groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        utilisationDuProduitRepository.save(groExisting);
    }

    @Override
    public UtilisationDuProduit findByNomDuProduit(String nomDuProduit) throws NotFoundException {

        return utilisationDuProduitRepository.findByNomDuProduit(nomDuProduit);    }

    @Override
    public Page<UtilisationDuProduitDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        List<UtilisationDuProduitDto>  result = utilisationDuProduitRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(utilisationDuProduitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public Page<UtilisationDuProduitDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<UtilisationDuProduitDto>  result = utilisationDuProduitRepository.findByNomDuProduitContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(utilisationDuProduitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
