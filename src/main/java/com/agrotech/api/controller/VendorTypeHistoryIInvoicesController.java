package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorTypeHistoryIInvoicesRepository;
import com.agrotech.api.dto.VendorTypeHistoryIInvoicesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.VendorTypeHistoryIInvoicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendortypehistoryiinvoices")
@RequiredArgsConstructor
public class VendorTypeHistoryIInvoicesController {



    private final VendorTypeHistoryIInvoicesService vendorTypeHistoryIInvoicesService ;


    private  final VendorTypeHistoryIInvoicesRepository vendorTypeHistoryIInvoicesRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorTypeHistoryIInvoicesRepository.deleteAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid VendorTypeHistoryIInvoicesDto warehouse) {
        VendorTypeHistoryIInvoicesDto response = vendorTypeHistoryIInvoicesService.create(warehouse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
