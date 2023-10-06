package com.agrotech.api.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.agrotech.api.Repository.FournisseurRepository;
import com.agrotech.api.Repository.SalesSkuRepository;
import com.agrotech.api.Repository.VendorSKURepository;
import com.agrotech.api.model.*;
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
import com.agrotech.api.services.ProduitService;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

	@Autowired
    private final ProduitRepository produitRepository;
	@Autowired
    private final ProduitMapper produitMapper;

    @Autowired
    private final FournisseurRepository fournisseurRepository;

    @Autowired
    private  final VendorSKURepository vendorSKURepository;

    @Autowired
    private  final SalesSkuRepository salesSkuRepository;

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
        Fournisseur fournisseur =new Fournisseur();
        VendorSKU vendorSKU=new VendorSKU();
        SalesSKU salesSKU = new SalesSKU();


        produit.setName(record.get("name"));
        produit.setCode(record.get("code"));
        produit.setType(record.get("type"));
        produit.setStatuss(Boolean.valueOf(record.get("statuss")));
        produit.setCurrency(record.get("currency"));
        produit.setInventaire(record.get("Inventaire"));
        produit.setMedicamenteux( Boolean.valueOf(record.get("Medicamenteux"))  );
        produit.setFabricant(record.get("Fabricant"));
        produit.setCouleur(record.get("couleur"));
        produit.setMaxdepasse(record.get("maxdepasse"));
        produit.setPrixUnitaireHt(new BigDecimal(record.get("prixUnitaireHt")));
        produit.setTauxTva(new BigDecimal(record.get("tauxTva")));
        produit.setIsDeleted( Boolean.valueOf(record.get("isDeleted"))  );
        fournisseur = fournisseurRepository.findByName(record.get("fournisseur"));
        produit.setFournisseur(fournisseur);
        produit.setCategory(record.get("category"));
        String t=record.get("transactionDate").split("/")[2]+"-"+record.get("transactionDate").split("/")[1]+"-"+record.get("transactionDate").split("/")[0];
        produit.setTransactionDate(t);
        System.out.println("::"+record);
        produit.setFarmCode(record.get("farmCode"));
        produit.setHouseCode(record.get("houseCode"));
        vendorSKU = vendorSKURepository.findByVendorSKUName(record.get("vendorSKU"));
        produit.setVendorSKU(vendorSKU);
        salesSKU = salesSkuRepository.findBySailorNameSku(record.get("salesSKU"));
        produit.setSalesSKU(salesSKU);


//        produit.setCategory(new Category((record.get("code")), (record.get("designation")), null));
        return produit;
    }
}
