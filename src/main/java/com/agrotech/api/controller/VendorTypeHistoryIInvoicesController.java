package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorTypeHistoryIInvoicesRepository;
import com.agrotech.api.dto.VendorTypeHistoryIInvoicesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.VendorTypeHistoryIInvoicesService;
import com.itextpdf.text.DocumentException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendortypehistoryiinvoices")
@RequiredArgsConstructor
public class VendorTypeHistoryIInvoicesController {



    private final VendorTypeHistoryIInvoicesService vendorTypeHistoryIInvoicesService ;


    private  final VendorTypeHistoryIInvoicesRepository vendorTypeHistoryIInvoicesRepository;
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorTypeHistoryIInvoicesRepository.deleteAll();
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid VendorTypeHistoryIInvoicesDto warehouse) throws DocumentException, FileNotFoundException {
        VendorTypeHistoryIInvoicesDto response = vendorTypeHistoryIInvoicesService.create(warehouse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
