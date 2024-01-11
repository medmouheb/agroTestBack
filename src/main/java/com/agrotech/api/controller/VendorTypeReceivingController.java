package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorTypeReceivingRepository;
import com.agrotech.api.dto.VendorTypeReceivingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypeReceiving;
import com.agrotech.api.services.VendorTypeReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendorTypeReceiving")
@RequiredArgsConstructor
public class VendorTypeReceivingController {

    private final VendorTypeReceivingService vendorTypeReceivingService ;


    private  final VendorTypeReceivingRepository vendorTypeReceivingRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorTypeReceivingRepository.deleteAll();
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorTypeReceivingDto vendorTypeReceiving) {
        VendorTypeReceivingDto response = vendorTypeReceivingService.create(vendorTypeReceiving);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorTypeReceivingDto campany) throws NotFoundException {
        VendorTypeReceivingDto response = vendorTypeReceivingService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorTypeReceivingDto response = vendorTypeReceivingService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorTypeReceivingDto> response = vendorTypeReceivingService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorTypeReceiving> response = vendorTypeReceivingService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorTypeReceivingDto response = vendorTypeReceivingService.findById(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorTypeReceivingService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorTypeReceivingService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorTypeReceivingService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorTypeReceiving> response = vendorTypeReceivingService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
