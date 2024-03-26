package com.agrotech.api.controller;


import com.agrotech.api.Repository.FactureRepository;
import com.agrotech.api.Repository.ProductionRepository;
import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.dto.ProductionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Facture;
import com.agrotech.api.model.Production;
import com.agrotech.api.model.User;
import com.agrotech.api.services.FactureService;
import com.agrotech.api.services.ProductionService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = { "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionController {


    private final ProductionService productionService;
    private final ProductionRepository productionRepository;


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProductionDto campany) throws DocumentException, FileNotFoundException {
        ProductionDto response = productionService.create(campany);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        productionRepository.deleteAll();
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProductionDto campany) throws NotFoundException {
        ProductionDto response = productionService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        ProductionDto response = productionService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<ProductionDto> response = productionService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String farmername,
            @RequestParam(defaultValue = "") String filter) {

        AtomicReference<String> t= new AtomicReference<>("admin");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getAuthorities().forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_FARMER")){
                t.set("farmer");
            }else if(authority.getAuthority().equals("ROLE_ADMIN")){
                t.set("admin");
            }else {
                t.set("employee");
            }
        });


        Page<Production> response;

        if(t.get().equals("admin")){
            response= productionService.getpages1(pageSize, pageNumber, filter);
        }else {
            response= productionService.getpages(pageSize, pageNumber, filter,farmername);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String farmername,
            @RequestParam(defaultValue = "") String filter) {

        AtomicReference<String> t= new AtomicReference<>("admin");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Page<User> users;
        // Replace with the actual method

        userDetails.getAuthorities().forEach(authority -> {
            if(authority.getAuthority().equals("ROLE_FARMER")){
                t.set("farmer");
            }else if(authority.getAuthority().equals("ROLE_ADMIN")){
                t.set("admin");
            }else {
                t.set("employee");
            }
        });
        Page<Production> response;

        if(t.get().equals("admin")){
            response= productionService.getpagesarchive(pageSize, pageNumber, filter);
        }else {
            response= productionService.getpagesarchive1(pageSize, pageNumber, filter,farmername);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        ProductionDto response = productionService.findByCode(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        productionService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        productionService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        productionService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
