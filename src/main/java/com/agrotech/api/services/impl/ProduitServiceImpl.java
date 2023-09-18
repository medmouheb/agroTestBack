package com.agrotech.api.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.ProduitMapper;
import com.agrotech.api.model.Category;
import com.agrotech.api.model.Growout;
import com.agrotech.api.model.Produit;
import com.agrotech.api.services.ProduitService;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

	@Autowired
    private final ProduitRepository produitRepository;
	@Autowired
    private final ProduitMapper produitMapper;

    private Produit save(Produit entity) {
        return produitRepository.save(entity);
    }

    @Override
    public ProduitDto create(ProduitDto dto) {
        return produitMapper.toDto(
                save(
                        produitMapper.toEntity(dto)
                )
        );
    }

    @Override
    @Transactional
    public void importCSV(List<CSVRecord> records) {
        List<Produit> list = new ArrayList<>();
        records.forEach(
                record -> list.add(recordToProduit(record))
        );
        produitRepository.saveAll(list);
    }

    @Override
    public Produit findByname(String name) throws NotFoundException {
        return produitRepository.findByName(name);
    }

    @Override
    public ProduitDto update(String id, ProduitDto dto) throws NotFoundException {
        Optional<Produit> optional = produitRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        Produit existing = optional.get();
        produitMapper.partialUpdate(existing, dto);
        return produitMapper.toDto(
                save(existing)
        );
    }

    @Override
    public ProduitDto findById(String id) throws NotFoundException {
        Optional<Produit> optional = produitRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        return produitMapper.toDto(optional.get());
    }


    @Override
    public List<ProduitDto> findAll() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProduitDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<ProduitDto>  result = produitRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(result);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if (!produitRepository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        produitRepository.deleteById(id);
    }

    @Override
    public ProduitDto findByCode(String code) throws NotFoundException {
        Optional<Produit> optional = produitRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        return produitMapper.toDto(optional.get());
    }

    @Override
    public Page<Produit> findPage1(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Produit>  result =  produitRepository.findByIsDeletedAndNameContainingIgnoreCase(false,filter, pageable);
        return result;
        // return new PageImpl<>(result);
    }

    @Override
    public Page<Produit> findArchivedPage1(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Produit>  result =  produitRepository.findByIsDeletedAndNameContainingIgnoreCase(true,filter, pageable);
        return result;
    }

    @Override
    public List<Produit> findAllByType(String type) {

        return produitRepository.findAll();
    }

    @Override
    public List<ProduitDto> findAllByCategoryId(String idCategory) {
        return produitRepository.findByCategory(idCategory)
                .stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Produit> groOptional =  produitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Growout not found ");
        }
        Produit groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        produitRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Produit> groOptional =  produitRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Growout not found ");
        }
        Produit groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        produitRepository.save(groExisting);

    }

    @Override
    public Page<ProduitDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<ProduitDto>  result = produitRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(produitMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    private Produit recordToProduit(CSVRecord record){
        Produit produit = new Produit();
        produit.setName(record.get("name"));
        produit.setCode(record.get("code"));
        produit.setType(record.get("type"));
        produit.setStatus(Boolean.valueOf(record.get("status")));
        produit.setInventaire(record.get("inventory"));
        produit.setMedicamenteux( Boolean.valueOf(record.get("medicated"))  );
        produit.setFabricant(record.get("manufacturer"));
        produit.setMaxdepasse(record.get("maxOver"));
        produit.setCouleur(record.get("color"));
        produit.setPrixUnitaireHt(new BigDecimal(record.get("unitPrice")));
        produit.setTauxTva(new BigDecimal(record.get("tva")));
//        produit.setCategory(new Category((record.get("code")), (record.get("designation")), null));
        return produit;
    }
}
