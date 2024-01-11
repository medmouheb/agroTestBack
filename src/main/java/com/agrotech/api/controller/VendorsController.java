package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorsRepository;
import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vendors;
import com.agrotech.api.services.VendorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorsController {


    private final VendorsService vendorsService ;
    private final VendorsRepository vendorsRepository;


    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorsRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorsDto vendors) {
        VendorsDto response = vendorsService.create(vendors);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorsDto vendors) throws NotFoundException {
        VendorsDto response = vendorsService.update(id, vendors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorsDto response = vendorsService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorsDto> response = vendorsService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vendors> response = vendorsService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorsDto response = vendorsService.findByVendorCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorsService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorsService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorsService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<Vendors> response = vendorsService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }






}
