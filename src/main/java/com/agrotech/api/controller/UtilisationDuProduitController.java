package com.agrotech.api.controller;


import com.agrotech.api.Repository.UtilisationDuProduitRepository;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.dto.UtilisationDuProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.UtilisationDuProduit;
import com.agrotech.api.services.ProduitService;
import com.agrotech.api.services.UtilisationDuProduitService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/UtilisationDuProduit")
@RequiredArgsConstructor
public class UtilisationDuProduitController {

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


    private final UtilisationDuProduitService utilisationDuProduitService;

    private final ProduitService produitService;
    private final UtilisationDuProduitRepository utilisationDuProduitRepository;

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/deleteall")
    public void deleteall() throws NotFoundException {
        utilisationDuProduitRepository.deleteAll();
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UtilisationDuProduitDto vehicle) throws DocumentException, FileNotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.create(vehicle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        UtilisationDuProduit response=utilisationDuProduitService.findByNomDuProduit(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody UtilisationDuProduitDto campany) throws NotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<UtilisationDuProduitDto> response = utilisationDuProduitService.findAllByFarmer(getusername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {

        if(getRole().equals("admin")){
            Page<UtilisationDuProduit> response = utilisationDuProduitService.getpages(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{

            if(getRole().equals("employee")){
                Page<UtilisationDuProduit> response = utilisationDuProduitService.getpagesFarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<UtilisationDuProduit> response = utilisationDuProduitService.getpagesFarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {


        if(getRole().equals("admin")){
            Page<UtilisationDuProduit> response = utilisationDuProduitService.getpagesarchive(pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{

            if(getRole().equals("employee")){
                Page<UtilisationDuProduit> response = utilisationDuProduitService.getpagesarchiveFarmer(userService.getFarmerByUsername(getusername()).get(),pageSize, pageNumber, filter);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Page<UtilisationDuProduit> response = utilisationDuProduitService.getpagesarchiveFarmer(getusername(),pageSize, pageNumber, filter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.findByCodeProduit(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        utilisationDuProduitService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        utilisationDuProduitService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        utilisationDuProduitService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



//get all produit code
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getAllproduit")
    public ResponseEntity<?> findAllProduit() {
        List<ProduitDto> response = produitService.findAll();
        List<String> codeProduit = new ArrayList<>();
        for (ProduitDto produitDto : response) {
            codeProduit.add(produitDto.getCode());
        }
        return new ResponseEntity<>(codeProduit, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")
    @GetMapping("/getAllproduit/{code}")
    public String findProduitName(@PathVariable String code) throws NotFoundException {
        ProduitDto produitDto = produitService.findByCode(code);
        String nameProduit = produitDto.getName();
        return nameProduit;
    }

}
