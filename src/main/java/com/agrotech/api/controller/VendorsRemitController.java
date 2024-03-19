package com.agrotech.api.controller;


import com.agrotech.api.Repository.VendorsRemitRepository;
import com.agrotech.api.dto.VendorsRemitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorsRemit;
import com.agrotech.api.services.VendorsRemitService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/vendorsRemit")
@RequiredArgsConstructor
public class VendorsRemitController {

    private final VendorsRemitService vendorsRemitService ;

    private  final VendorsRemitRepository vendorsRemitRepository;
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        vendorsRemitRepository.deleteAll();
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody VendorsRemitDto vendorSku) throws DocumentException, FileNotFoundException {
        VendorsRemitDto response = vendorsRemitService.create(vendorSku);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VendorsRemitDto> response = vendorsRemitService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findByName(@PathVariable String findByRemitToName) throws NotFoundException{
        VendorsRemit response=vendorsRemitService.findByRemitToName(findByRemitToName);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody VendorsRemitDto vendorsRemit) throws NotFoundException {
        VendorsRemitDto response = vendorsRemitService.update(id, vendorsRemit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        VendorsRemitDto response = vendorsRemitService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsRemit> response = vendorsRemitService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        VendorsRemitDto response = vendorsRemitService.findById(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        vendorsRemitService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        vendorsRemitService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        vendorsRemitService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<VendorsRemit> response = vendorsRemitService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
