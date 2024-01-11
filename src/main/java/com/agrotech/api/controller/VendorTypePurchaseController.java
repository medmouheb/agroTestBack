package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorTypePurchaserRepository;
import com.agrotech.api.dto.VendorTypePurchaserDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypePurchaser;
import com.agrotech.api.services.VendorTypePurchaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendorTypePurchase")
@RequiredArgsConstructor
public class VendorTypePurchaseController {


    private final VendorTypePurchaserService vendorTypePurchaseService ;

    private  final VendorTypePurchaserRepository vendorTypePurchaserRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorTypePurchaserRepository.deleteAll();
    }



    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorTypePurchaserDto vendorTypePurchase) {
        VendorTypePurchaserDto response = vendorTypePurchaseService.create(vendorTypePurchase);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorTypePurchaserDto vendorTypePurchaser) throws NotFoundException {
        VendorTypePurchaserDto response = vendorTypePurchaseService.update(id, vendorTypePurchaser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorTypePurchaserDto response = vendorTypePurchaseService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorTypePurchaserDto> response = vendorTypePurchaseService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorTypePurchaser> response = vendorTypePurchaseService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorTypePurchaserDto response = vendorTypePurchaseService.findById(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorTypePurchaseService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorTypePurchaseService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorTypePurchaseService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorTypePurchaser> response = vendorTypePurchaseService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
