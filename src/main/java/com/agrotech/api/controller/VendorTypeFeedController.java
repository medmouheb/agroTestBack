package com.agrotech.api.controller;

import com.agrotech.api.Repository.VendorTypeFeedRepository;
import com.agrotech.api.dto.VendorTypeFeedDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypeFeed;
import com.agrotech.api.services.VendorTypeFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendortypefeed")
@RequiredArgsConstructor
public class VendorTypeFeedController {



    private final VendorTypeFeedService vendorTypeFeedService ;

    private  final VendorTypeFeedRepository vendorTypeFeedRepository;
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorTypeFeedRepository.deleteAll();
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorTypeFeedDto vendorTypeFeed) {
        VendorTypeFeedDto response = vendorTypeFeedService.create(vendorTypeFeed);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        VendorTypeFeed response=vendorTypeFeedService.findByPOState(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorTypeFeedDto vendorTypeFeed) throws NotFoundException {
        VendorTypeFeedDto response = vendorTypeFeedService.update(id, vendorTypeFeed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorTypeFeedDto response = vendorTypeFeedService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorTypeFeedDto> response = vendorTypeFeedService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorTypeFeed> response = vendorTypeFeedService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorTypeFeedDto response = vendorTypeFeedService.findById(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorTypeFeedService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorTypeFeedService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorTypeFeedService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorTypeFeed> response = vendorTypeFeedService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
