package com.agrotech.api.controller;


import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.dto.UtilisationDuProduitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Division;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.UtilisationDuProduit;
import com.agrotech.api.services.ProduitService;
import com.agrotech.api.services.UtilisationDuProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/UtilisationDuProduit")
@RequiredArgsConstructor
public class UtilisationDuProduitController {


    private final UtilisationDuProduitService utilisationDuProduitService;

    private final ProduitService produitService;



    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UtilisationDuProduitDto vehicle) {
        UtilisationDuProduitDto response = utilisationDuProduitService.create(vehicle);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<?> findbyname(@PathVariable String name) throws NotFoundException {
        UtilisationDuProduit response=utilisationDuProduitService.findByNomDuProduit(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody UtilisationDuProduitDto campany) throws NotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.update(id, campany);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<UtilisationDuProduitDto> response = utilisationDuProduitService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findPage(
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<UtilisationDuProduit> response = utilisationDuProduitService.getpages(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
        UtilisationDuProduitDto response = utilisationDuProduitService.findByCodeProduit(code);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
        utilisationDuProduitService.delete(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/archiver/{id}")
    public ResponseEntity<?> archive(@PathVariable String id) throws NotFoundException {
        utilisationDuProduitService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/desarchiver/{id}")
    public ResponseEntity<?> setNotArchive(@PathVariable String id) throws NotFoundException {
        utilisationDuProduitService.setNotArchive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/archived/page")
    public ResponseEntity<?> findArchivedPage(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "") String filter
    ) {
        Page<UtilisationDuProduit> response = utilisationDuProduitService.getpagesarchive(pageSize, pageNumber, filter);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//get all produit code
    @GetMapping("/getAllproduit")
    public ResponseEntity<?> findAllProduit() {
        List<ProduitDto> response = produitService.findAll();
        List<String> codeProduit = new ArrayList<>();
        for (ProduitDto produitDto : response) {
            codeProduit.add(produitDto.getCode());
        }
        return new ResponseEntity<>(codeProduit, HttpStatus.OK);
    }

    @GetMapping("/getAllproduit/{code}")
    public String findProduitName(@PathVariable String code) throws NotFoundException {
        //find the produitname by code
        ProduitDto produitDto = produitService.findByCode(code);
        //get the name of codeproduit selement
        String nameProduit = produitDto.getName();
        //get the list of all produit
        return nameProduit;
    }

}
