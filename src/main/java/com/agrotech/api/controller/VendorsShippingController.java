package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorsShippingRepository;
import com.agrotech.api.dto.VendorsShippingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorsShipping;
import com.agrotech.api.services.VendorsShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendorsShipping")
@RequiredArgsConstructor
public class VendorsShippingController  {

    private final VendorsShippingService vendorsShippingService ;

    private  final VendorsShippingRepository vendorsShippingRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorsShippingRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorsShippingDto vendorsShipping) {
        VendorsShippingDto response = vendorsShippingService.create(vendorsShipping);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        VendorsShipping response=vendorsShippingService.findByShippingLocationName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorsShippingDto vendorsShipping) throws NotFoundException {
        VendorsShippingDto response = vendorsShippingService.update(id, vendorsShipping);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorsShippingDto response = vendorsShippingService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorsShippingDto> response = vendorsShippingService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsShipping> response = vendorsShippingService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorsShippingDto response = vendorsShippingService.findByShippingLocationCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorsShippingService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorsShippingService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorsShippingService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsShipping> response = vendorsShippingService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
