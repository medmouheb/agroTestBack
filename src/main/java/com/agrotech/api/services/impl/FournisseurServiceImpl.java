package com.agrotech.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.agrotech.api.dto.CampanyDto;
import lombok.RequiredArgsConstructor;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agrotech.api.Repository.FournisseurRepository;
import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.FournisseurMapper;
import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.services.FournisseurService;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

	@Autowired
    private final FournisseurRepository fournisseurRepository;
	@Autowired
    private final FournisseurMapper fournisseurMapper;

    @Autowired
    private final NotificationService notificationService;


    private Fournisseur save(Fournisseur entity) {
        return fournisseurRepository.save(entity);
    }


    public Fournisseur savex(Fournisseur entity) {
        return fournisseurRepository.save(entity);
    }

    @Override
    public FournisseurDto create(FournisseurDto dto) {

        FournisseurDto createdDto = fournisseurMapper.toDto(save(fournisseurMapper.toEntity(dto)));
        notificationService.addNotification("Fournisseur created: " + createdDto.getName());
        return createdDto;
    }



    @Override
    @Transactional
    public void importCSV(List<CSVRecord> records) {
        List<Fournisseur> list = new ArrayList<>();
        records.forEach(
                record -> list.add(recordToFournisseur(record))
        );
        fournisseurRepository.saveAll(list);
    }

    @Override
    public FournisseurDto update(String id, FournisseurDto dto) throws NotFoundException {
        // Fetch existing Fournisseur
        Optional<Fournisseur> optional = fournisseurRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found");
        }

        Fournisseur existing = optional.get();

        // Update Fournisseur with partial data
        fournisseurMapper.partialUpdate(existing, dto);

        // Save the updated Fournisseur
        Fournisseur updatedFournisseur = fournisseurRepository.save(existing); // Ensure this is only called once
        FournisseurDto updatedDto = fournisseurMapper.toDto(updatedFournisseur);

        // Add notification
        notificationService.addNotification("Fournisseur updated: " + updatedDto.getName());

        // Return updated Fournisseur DTO
        return updatedDto;
    }


    @Override
    public FournisseurDto findById(String id) throws NotFoundException {
        Optional<Fournisseur> optional = fournisseurRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found");
        }
        return fournisseurMapper.toDto(optional.get());
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FournisseurDto> findAllByfarmer(String farmer) {
        return fournisseurRepository.findByFarmer(farmer)
                .stream()
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Fournisseur> findPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Fournisseur>  result =  fournisseurRepository.findByNameContainingIgnoreCaseAndIsDeleted(filter,false,pageable);
        return result;
    }

    @Override
    public Page<Fournisseur> findPage1Farmer(String farmer,int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Page<Fournisseur>  result =  fournisseurRepository.findByFarmerAndNameContainingIgnoreCaseAndIsDeleted(farmer,filter,false,pageable);
        return result;
    }

    @Override
    public Page<FournisseurDto> findPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<FournisseurDto>  result = fournisseurRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                //.filter(g->(g.getIsDeleted() == null || !g.getIsDeleted()))
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());
                return new PageImpl<>(result);
    }

    @Override
    public Fournisseur findByname(String name) throws NotFoundException {
        return fournisseurRepository.findByName(name);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        if(!fournisseurRepository.existsById(id)){
            throw new NotFoundException("Fournisseur not found");
        }
        fournisseurRepository.deleteById(id);
    }

    @Override
    public FournisseurDto findByCode(String code , String farmer) throws NotFoundException {
        Optional<Fournisseur> optional = fournisseurRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found");
        }
        return fournisseurMapper.toDto(optional.get());
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<Fournisseur> groOptional =  fournisseurRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found ");
        }
        Fournisseur groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        fournisseurRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<Fournisseur> groOptional =  fournisseurRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Fournisseur not found ");
        }
        Fournisseur groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        fournisseurRepository.save(groExisting);

    }

    @Override
    public Page<Fournisseur> findArchivedPage1(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return fournisseurRepository.findByNameContainingIgnoreCaseAndIsDeleted(filter,true,pageable);
    }

    @Override
    public Page<Fournisseur> findArchivedPage1Farmer(String farmer,int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return fournisseurRepository.findByFarmerAndNameContainingIgnoreCaseAndIsDeleted(farmer,filter,true,pageable);
    }

    @Override
    public Page<FournisseurDto> findArchivedPage(int pageSize, int pageNumber, String filter) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize
        );
        List<FournisseurDto>  result = fournisseurRepository.findByNameContainingIgnoreCase(filter, pageable)
                .stream()
                .filter(g->g.getIsDeleted()!=null && g.getIsDeleted())
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    private Fournisseur recordToFournisseur(CSVRecord record){
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setName(record.get("name"));
        fournisseur.setEmail(record.get("email"));
        fournisseur.setPhone(record.get("phone"));
        fournisseur.setType(record.get("type"));
        fournisseur.setCurrency(record.get("currencyCode"));
        fournisseur.setPaymentTerm(record.get("paymentTerm"));
        fournisseur.setAddress(record.get("address"));
        fournisseur.setCodeCity(record.get("codeCity"));
        fournisseur.setNameCity(record.get("nameCity"));
        fournisseur.setWilayaName(record.get("wilayaName"));
        return fournisseur;
    }
}
