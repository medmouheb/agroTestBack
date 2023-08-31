package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.RapprochementDesStocksRepository;
import com.agrotech.api.dto.RapprochementDesStocksDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.RapprochementDesStocksMapper;
import com.agrotech.api.model.RapprochementDesStocks;
import com.agrotech.api.services.RapprochementDesStocksService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RapprochementDesStocksServiceImpl implements RapprochementDesStocksService {




    @Autowired
    private RapprochementDesStocksRepository rapprochementDesStocksRepository;
    @Autowired
    private RapprochementDesStocksMapper rapprochementDesStocksMapper;


    //save
    public RapprochementDesStocks save(RapprochementDesStocks entity) {

        return rapprochementDesStocksRepository.save(entity);

    }
    @Override
    public RapprochementDesStocksDto create(RapprochementDesStocksDto dto) {

        return rapprochementDesStocksMapper.toDto(save(rapprochementDesStocksMapper.toEntity(dto)));
    }

    @Override
    public RapprochementDesStocksDto update(String id, RapprochementDesStocksDto dto) throws NotFoundException {
        Optional<RapprochementDesStocks> camOptional =  rapprochementDesStocksRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("Rapprochement Des Stocks not found ");
        }

        RapprochementDesStocks campanyExisting = camOptional.get();
        rapprochementDesStocksMapper.partialUpdate(campanyExisting, dto);

        return rapprochementDesStocksMapper.toDto(save(campanyExisting));    }

    @Override
    public RapprochementDesStocksDto findById(String id) throws NotFoundException {
        Optional<RapprochementDesStocks> campOptional = rapprochementDesStocksRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Rapprochement Des Stocks not found ");
        }
        return rapprochementDesStocksMapper.toDto(campOptional.get());    }

    @Override
    public List<RapprochementDesStocksDto> findAll() {
        return rapprochementDesStocksRepository.findAll().stream()
                .map(rapprochementDesStocksMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RapprochementDesStocksDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<RapprochementDesStocksDto>  result =  rapprochementDesStocksRepository.findByNomDuProduitContainingIgnoreCase(filter, pageable)
                .stream()
//				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(rapprochementDesStocksMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);

    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!rapprochementDesStocksRepository.existsById(id)) {
            throw new NotFoundException("Rapprochement Des Stocks not found ");
        }
        rapprochementDesStocksRepository.deleteById(id);

    }



    @Override
    public RapprochementDesStocksDto findBynDeReference(String nDeReference) throws NotFoundException {
        Optional<RapprochementDesStocks> campOptional = rapprochementDesStocksRepository.findBynDeReference(nDeReference);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Rapprochement Des Stocks not found ");
        }
        return rapprochementDesStocksMapper.toDto(campOptional.get());
    }

    @Override
    public Page<RapprochementDesStocksDto> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        List<RapprochementDesStocksDto> result =  rapprochementDesStocksRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(false,filter, pageable)
                .stream()
////				.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(rapprochementDesStocksMapper::toDto)
                .collect(Collectors.toList());
        //return result;
        return new PageImpl<>(result);
    }

    @Override
    public Page<RapprochementDesStocks> getpages(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        Page<RapprochementDesStocks> result =  rapprochementDesStocksRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(false, filter,pageable);

        return result;
    }

    @Override
    public Page<RapprochementDesStocks> getpagesarchive(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        Page<RapprochementDesStocks> result =  rapprochementDesStocksRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(true, filter,pageable);

        return result;
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<RapprochementDesStocks> groOptional =  rapprochementDesStocksRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Rapprochement Des Stocks not found ");
        }
        RapprochementDesStocks groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        rapprochementDesStocksRepository.save(groExisting);
    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<RapprochementDesStocks> groOptional =  rapprochementDesStocksRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Rapprochement Des Stocks not found ");
        }
        RapprochementDesStocks groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        rapprochementDesStocksRepository.save(groExisting);
    }
    @Override
    public RapprochementDesStocks findByNomDuProduits(String nomDuProduit) throws NotFoundException {
        return rapprochementDesStocksRepository.findByNomDuProduit(nomDuProduit);
    }

    @Override
    public Page<RapprochementDesStocksDto> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("nomDuProduit").ascending());
        List<RapprochementDesStocksDto>  result = rapprochementDesStocksRepository.findByIsDeletedAndNomDuProduitContainingIgnoreCase(true,filter, pageable)
                .stream()
                //.filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(rapprochementDesStocksMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);    }


    @Override
    public Page<RapprochementDesStocksDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<RapprochementDesStocksDto> result = rapprochementDesStocksRepository.findByNomDuProduitContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(rapprochementDesStocksMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }
}
