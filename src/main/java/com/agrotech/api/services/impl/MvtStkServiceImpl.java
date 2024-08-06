package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.MvtStkRepository;
import com.agrotech.api.Repository.ProduitRepository;
import com.agrotech.api.Repository.StockRepository;
import com.agrotech.api.dto.MvtStkDto;

import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.MvtStkMapper;

import com.agrotech.api.model.MvtStk;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.Stock;
import com.agrotech.api.services.MvtStkService;

import com.agrotech.api.utils.EmailService;
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
public class MvtStkServiceImpl implements MvtStkService {

    @Autowired
    private MvtStkRepository mvtStkRepository ;
    @Autowired
    private ProduitRepository produitRepository ;
    @Autowired
    private StockRepository StockRepository ;

    @Autowired
    private MvtStkMapper mvtStkMapper ;
    @Autowired
    private EmailService emailService;




    public MvtStk save(MvtStk dto) {

        return mvtStkRepository.save(dto);

    }

    @Override
    public MvtStkDto createwithmail(MvtStkDto dto, String email) {
        Stock s = dto.getStock();
        String emailMessage = "";
        boolean emailRequired = false;

        // Update stock quantity
        if (dto.getTypeMvt().equals("enter")) {
            s.setQuantity(s.getQuantity() + dto.getQuantite().floatValue());
        } else {
            s.setQuantity(s.getQuantity() - dto.getQuantite().floatValue());
        }
        StockRepository.save(s);

        Produit p = produitRepository.findByName(dto.getStock().getProduct());
        if (p != null) {
            if (p.getStockMinimumAlert() != null && s.getQuantity() < p.getStockMinimumAlert().floatValue()) {


                emailMessage += "Stock is below the minimum alert level.\n";
                emailRequired = true;
            }
            if (s.getQuantity() > p.getMaxdepasse().floatValue()) {
                emailMessage += "Stock exceeds the maximum allowed level.\n";
                emailRequired = true;
            }
        } else {
            emailMessage += "Product not found: " + dto.getStock().getProduct() + "\n";
            emailRequired = true;

            // Handle the case where the product is not found
        }

        // Send email if needed
        if (emailRequired) {
            System.out.println("Email required");
            System.out.println(email);
            System.out.println(emailMessage);
            // Assume `getEmail()` returns the recipient's email
            emailService.sendStockAlertMail(email, "Stock Alert", emailMessage);
        }

        return mvtStkMapper.toDto(save(mvtStkMapper.toEntity(dto)));
    }


    @Override
    public MvtStkDto create(MvtStkDto dto) throws DocumentException, FileNotFoundException {
        Stock s = dto.getStock();
        String emailMessage = "";
        boolean emailRequired = false;

        // Update stock quantity
        if (dto.getTypeMvt().equals("enter")) {
            s.setQuantity(s.getQuantity() + dto.getQuantite().floatValue());
        } else {
            s.setQuantity(s.getQuantity() - dto.getQuantite().floatValue());
        }
        StockRepository.save(s);

        // Check conditions and prepare email message
        Produit p = produitRepository.findByName(dto.getStock().getProduct());
        if (p != null) {
            if (p.getStockMinimumAlert() != null && s.getQuantity() < p.getStockMinimumAlert().floatValue()) {
                emailMessage += "Stock is below the minimum alert level.\n";
                emailRequired = true;
            }
            if (s.getQuantity() > p.getMaxdepasse().floatValue()) {
                emailMessage += "Stock exceeds the maximum allowed level.\n";
                emailRequired = true;



            }
        } else {
            emailMessage += "Product not found: " + dto.getStock().getProduct() + "\n";
            // Handle the case where the product is not found
        }

        // Send email if needed
        if (emailRequired) {
            // Assume `getEmail()` returns the recipient's email
            String email = getEmailForAlert(); // Implement this method to get the recipient's email
            emailService.sendStockAlertMail(email, "Stock Alert", emailMessage);
        }

        return mvtStkMapper.toDto(save(mvtStkMapper.toEntity(dto)));
    }

    // Implement this method to get the recipient's email based on your application logic
    private String getEmailForAlert() {
        // Logic to retrieve the email address
        return "mohamedmouheb@gmail.com"; // Replace with actual logic
    }



    @Override
    public MvtStkDto update(String id, MvtStkDto dto) throws NotFoundException {

        Optional<MvtStk> camOptional =  mvtStkRepository.findById(id);
        if(camOptional.isEmpty()) {
            throw new NotFoundException("MvtStk not found ");
        }

        MvtStk campanyExisting = camOptional.get();
        mvtStkMapper.partialUpdate(campanyExisting, dto);

        return mvtStkMapper.toDto(save(campanyExisting));

    }

    @Override
    public MvtStkDto findById(String id) throws NotFoundException {
        Optional<MvtStk> campOptional = mvtStkRepository.findById(id);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("MvtStk not found ");
        }
        return mvtStkMapper.toDto(campOptional.get());
    }

    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll().stream()
                .map(mvtStkMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) throws NotFoundException {

        if(!mvtStkRepository.existsById(id)) {
            throw new NotFoundException("MvtStk not found ");
        }

        mvtStkRepository.deleteById(id);


    }


    @Override
    public MvtStkDto findByCode(String code , String farmer) throws NotFoundException {
        Optional<MvtStk> campOptional = mvtStkRepository.findByCodeAndFarmer(code, farmer);
        if(campOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        return mvtStkMapper.toDto(campOptional.get());
    }


    @Override
    public Page<MvtStk> getpages(int pageSize, int pageNumber, String filter , String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(false,filter,farmername, pageable);


    }

    @Override
    public Page<MvtStk> getpages1(int pageSize, int pageNumber, String filter ) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCase(false,filter, pageable);


    }

    @Override
    public Page<MvtStk> getpagesarchive(int pageSize, int pageNumber, String filter) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCase(true,filter, pageable);

    }

    @Override
    public Page<MvtStk> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return  mvtStkRepository.findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(true,filter,farmername, pageable);

    }


    @Override
    public Page<MvtStkDto> findPage(int pageSize, int pageNumber, String filter) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<MvtStkDto>  result = mvtStkRepository.findByCodeContainingIgnoreCase(filter, pageable)
                .stream()
                .map(mvtStkMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    @Override
    public void archive(String id) throws NotFoundException {
        Optional<MvtStk> groOptional =  mvtStkRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        MvtStk groExisting = groOptional.get();
        groExisting.setIsDeleted(true);
        mvtStkRepository.save(groExisting);

    }

    @Override
    public void setNotArchive(String id) throws NotFoundException {
        Optional<MvtStk> groOptional =  mvtStkRepository.findById(id);
        if(groOptional.isEmpty()) {
            throw new NotFoundException("Crop not found ");
        }
        MvtStk groExisting = groOptional.get();
        groExisting.setIsDeleted(false);
        mvtStkRepository.save(groExisting);

    }





}
