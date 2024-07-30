package com.agrotech.api.controller;


import com.agrotech.api.Repository.CampanyRepository;
import com.agrotech.api.Repository.TaxRepository;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.TaxDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Tax;
import com.agrotech.api.services.CampanyService;
import com.agrotech.api.services.TaxService;
import com.agrotech.api.services.impl.UserService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("tax")
@RequiredArgsConstructor
public class TaxController {
    @Autowired
    private UserService userService;
    private String getRole() {
        AtomicReference<String> role = new AtomicReference<>("employee"); // default to "employee"
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        userDetails.getAuthorities().forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_FARMER")) {
                role.set("farmer");
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                role.set("admin");
            }
        });

        return role.get();
    }

    private String getusername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();

    }

    private final TaxService taxService;
    private final TaxRepository taxRepository;

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        taxRepository.deleteAll();
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody TaxDto campany) throws DocumentException, FileNotFoundException {
        TaxDto response = taxService.create(campany);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        Tax response = taxService.findByname(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TaxDto campany) throws NotFoundException {
        TaxDto response = taxService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        TaxDto response = taxService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<TaxDto> response = taxService.findAllByfarmer(getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        if(getRole().equals("admin")){
            Page<Tax> response = taxService.getpages(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            if(getRole().equals("employee")){
                Page<Tax> response = taxService.getpagesFarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<Tax> response = taxService.getpagesFarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter) {
        if(getRole().equals("admin")){
            Page<Tax> response = taxService.getpagesarchive(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            if(getRole().equals("employee")){
                Page<Tax> response = taxService.getpagesarchivefarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<Tax> response = taxService.getpagesarchivefarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        TaxDto response = taxService.findByCode(code,getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        taxService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        taxService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        taxService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
