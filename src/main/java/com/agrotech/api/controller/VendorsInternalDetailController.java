package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorsInternalDetailRepository;
import com.agrotech.api.dto.VendorsInternalDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorsInternalDetails;
import com.agrotech.api.services.VendorsInternalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendorsinternaldetails")
@RequiredArgsConstructor
public class VendorsInternalDetailController {


    final private VendorsInternalDetailService vendorsInternalDetailService ;


    private  final VendorsInternalDetailRepository vendorsInternalDetailRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorsInternalDetailRepository.deleteAll();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorsInternalDetailsDto vendorsInternalDetails) {
        VendorsInternalDetailsDto response = vendorsInternalDetailService.create(vendorsInternalDetails);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorsInternalDetailsDto vendorsInternalDetail) throws NotFoundException {
        VendorsInternalDetailsDto response = vendorsInternalDetailService.update(id, vendorsInternalDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorsInternalDetailsDto response = vendorsInternalDetailService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorsInternalDetailsDto> response = vendorsInternalDetailService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsInternalDetails> response = vendorsInternalDetailService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorsInternalDetailsDto response = vendorsInternalDetailService.findById(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorsInternalDetailService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorsInternalDetailService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorsInternalDetailService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsInternalDetails> response = vendorsInternalDetailService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
